package rent_a_car.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rent_a_car.exception.ResourceNotFoundException;
import rent_a_car.model.Booking;
import rent_a_car.repository.BookingRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

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
        return bookingRepository.save(booking);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable int id) {
        return bookingRepository.findById(id)
                .map(booking -> {
                    bookingRepository.delete(booking);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Booking not found with id " + id));
    }
}
