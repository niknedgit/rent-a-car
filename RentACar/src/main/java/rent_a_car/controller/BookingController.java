package rent_a_car.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rent_a_car.dto.AccountDto;
import rent_a_car.dto.BookingDto;
import rent_a_car.dto.CarDto;
import rent_a_car.exception.ResourceNotFoundException;
import rent_a_car.model.Account;
import rent_a_car.model.Booking;
import rent_a_car.model.Car;
import rent_a_car.model.Dates;
import rent_a_car.repository.AccountRepository;
import rent_a_car.repository.BookingRepository;
import rent_a_car.repository.CarRepository;
import rent_a_car.repository.DatesRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private DatesRepository datesRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private AccountRepository accountRepository;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(path = "/secured/all")
    public List<BookingDto> getBookings(){

        return bookingRepository.findAll()
                .stream().map(BookingDto::new).collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(path = "/secured/{id}")
    public BookingDto getBooking(@PathVariable("id") @NotNull int id){

        Optional<Booking> booking = bookingRepository.findById(id);
        booking
                .orElseThrow(()-> new ResourceNotFoundException("Booking not found with id " + id));
        return booking.map(BookingDto::new).get();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @PostMapping(path = "/secured/add")
    public BookingDto addBooking(@NotNull @Valid @RequestBody BookingDto bookingDto, Principal principal)
            throws Exception {

        if(bookingDto.getPickUpDate().isAfter(bookingDto.getDropOffDate()))
            throw new Exception("pickUpDate must be before dropOffDate");

        Optional<Car> carOptional = carRepository.findById(bookingDto.getCarId());
        Optional<Account> accountOptional = accountRepository.findByUsername(principal.getName());

        if(!carOptional.isPresent() || !accountOptional.isPresent())
            throw new ResourceNotFoundException("Resource not found");
        else {
            if (carOptional.get().getDates().isEmpty())
                throw new ResourceNotFoundException("Currently not possible");

            long noOfDaysBetween = ChronoUnit.DAYS.between(bookingDto.getPickUpDate(), bookingDto.getDropOffDate());

            Booking booking = new Booking(bookingDto.getPickUpDate(), bookingDto.getDropOffDate(),
                    (noOfDaysBetween + 1) * carOptional.get().getPricePerDay(),
                    carOptional.get(), accountOptional.get());

            LocalDate from = booking.getPickUpDate();
            LocalDate to = booking.getDropOffDate();

            Dates dates;

            for (Dates d : booking.getCar().getDates()) {
                if ((d.getDateFrom().isBefore(from) || d.getDateFrom().isEqual(from))
                        && (d.getDateTo().isAfter(to) || d.getDateTo().isEqual(to))) {
                    dates = d;

                    if (d.getDateFrom().isBefore(from)) {
                        Dates dates1 = new Dates(dates.getDateFrom(), from.minusDays(1), dates.getCarId());
                        datesRepository.save(dates1);
                    }

                    if (d.getDateTo().isAfter(to)) {
                        Dates dates2 = new Dates(to.plusDays(1), dates.getDateTo(), dates.getCarId());
                        datesRepository.save(dates2);
                    }

                    datesRepository.delete(dates);
                    return new BookingDto(bookingRepository.save(booking));
                }
            }
        }
        throw new ResourceNotFoundException("Currently not possible");
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(path = "/secured/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable int id) {

        return bookingRepository.findById(id)
                .map(booking -> {
                    bookingRepository.delete(booking);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Booking not found with id " + id));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(path = "/secured/{bookingId}")
    public BookingDto updateBooking(@PathVariable int bookingId,
                                 @Valid @RequestBody BookingDto bookingRequest) {

        return bookingRepository.findById(bookingId)
                .map(booking -> {
                    booking.setPickUpDate(bookingRequest.getPickUpDate());
                    booking.setDropOffDate(bookingRequest.getDropOffDate());
                    booking.setAccount(accountRepository.findById(bookingRequest.getAccountId()).get());
                    booking.setCar(carRepository.findById(bookingRequest.getCarId()).get());
                    return new BookingDto(bookingRepository.save(booking));
                }).orElseThrow(() -> new ResourceNotFoundException("Booking not found with id " + bookingId));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(path = "/secured/{bookingId}/account")
    public AccountDto getAccount(@PathVariable int bookingId){

        return new AccountDto(bookingRepository.findById(bookingId).get().getAccount());
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(path = "/secured/{bookingId}/car")
    public CarDto getCar(@PathVariable int bookingId){

        return new CarDto(bookingRepository.findById(bookingId).get().getCar());
    }
}
