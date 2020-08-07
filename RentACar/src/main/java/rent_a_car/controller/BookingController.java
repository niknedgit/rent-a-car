package rent_a_car.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rent_a_car.exception.ResourceNotFoundException;
import rent_a_car.model.Booking;
import rent_a_car.model.Dates;
import rent_a_car.repository.BookingRepository;
import rent_a_car.repository.DatesRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private DatesRepository datesRepository;

    @GetMapping(path = "/all")
    public List<Booking> getBookings(){
        return bookingRepository.findAll();
    }

    @GetMapping(path = "{id}")
    public Booking getBooking(@PathVariable("id") @NotNull int id){
        return bookingRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Booking not found with id " + id));
    }

    @PostMapping
    public Booking addBooking(@NotNull @Valid @RequestBody Booking booking){

        if(booking.getCar().getDates().isEmpty()) throw new ResourceNotFoundException("Currently not possible");

        LocalDate from = booking.getPickUpDate();
        LocalDate to = booking.getDropOffDate();

        Dates dates;

        for(Dates d: booking.getCar().getDates())
        {
            if ((d.getDateFrom().isBefore(from) || d.getDateFrom().isEqual(from))
                    && (d.getDateTo().isAfter(to) || d.getDateTo().isEqual(to)))
            {
                dates = d;

                if(d.getDateFrom().isBefore(from)) {
                    Dates dates1 = new Dates();
                    dates1.setCarId(dates.getCarId());
                    dates1.setDateFrom(dates.getDateFrom());
                    dates1.setDateTo(from.minusDays(1));
                    datesRepository.save(dates1);
                }

                if(d.getDateTo().isAfter(to)) {
                    Dates dates2 = new Dates();
                    dates2.setCarId(dates.getCarId());
                    dates2.setDateFrom(to.plusDays(1));
                    dates2.setDateTo(dates.getDateTo());
                    datesRepository.save(dates2);
                }

                datesRepository.delete(dates);

                return bookingRepository.save(booking);
            }
        }

        throw new ResourceNotFoundException("Currently not possible");
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable int id) {
        return bookingRepository.findById(id)
                .map(booking -> {
                    bookingRepository.delete(booking);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Booking not found with id " + id));
    }

    @PutMapping(path = "{bookingId}")
    public Booking updateBooking(@PathVariable int bookingId,
                                 @Valid @RequestBody Booking bookingRequest) {
        return bookingRepository.findById(bookingId)
                .map(booking -> {
                    booking.setPickUpDate(bookingRequest.getPickUpDate());
                    booking.setDropOffDate(bookingRequest.getDropOffDate());
                    booking.setName(bookingRequest.getName());
                    booking.setPhone(bookingRequest.getPhone());
                    booking.setEmail(bookingRequest.getEmail());
                    booking.setCar(bookingRequest.getCar());
                    return bookingRepository.save(booking);
                }).orElseThrow(() -> new ResourceNotFoundException("Booking not found with id " + bookingId));
    }
}
