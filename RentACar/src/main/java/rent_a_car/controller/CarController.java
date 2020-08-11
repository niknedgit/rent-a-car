package rent_a_car.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rent_a_car.dto.BookingDto;
import rent_a_car.dto.CarDto;
import rent_a_car.dto.DatesDto;
import rent_a_car.model.Booking;
import rent_a_car.model.Car;
import rent_a_car.exception.ResourceNotFoundException;
import rent_a_car.model.Dates;
import rent_a_car.repository.BookingRepository;
import rent_a_car.repository.CarRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/car")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping(path = "/all")
    public List<CarDto> getAll(){

        return carRepository.findAll()
                .stream().map(CarDto::new).collect(Collectors.toList());
    }

    @GetMapping(path = "{id}")
    public CarDto getCar(@PathVariable("id") @NotNull int id){

        Optional<Car> car = carRepository.findById(id);
        car
                .orElseThrow(()->new ResourceNotFoundException("Car not found with id " + id));
        return car.map(CarDto::new).get();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(path = "/secured/add")
    public CarDto addCar(@NotNull @Valid @RequestBody CarDto carDto){

        Car car = new Car(carDto.getMake(), carDto.getModel(), carDto.getImageUrl(), carDto.getPricePerDay());
        return new CarDto(carRepository.save(car));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(path = "/secured/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable int id) {

        return carRepository.findById(id)
                .map(car -> {
                    carRepository.delete(car);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Car not found with id " + id));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(path = "/secured/{carId}")
    public CarDto updateCar(@PathVariable int carId,
                                   @Valid @RequestBody CarDto carRequest) {

        return carRepository.findById(carId)
                .map(car -> {
                    car.setImageUrl(carRequest.getImageUrl());
                    car.setMake(carRequest.getMake());
                    car.setModel(carRequest.getModel());
                    car.setPricePerDay(carRequest.getPricePerDay());
                    return new CarDto(carRepository.save(car));
                }).orElseThrow(() -> new ResourceNotFoundException("Car not found with id " + carId));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(path = "/secured/{carId}/bookings")
    public List<BookingDto> getBookingSet(@PathVariable int carId){

        return carRepository.findById(carId).get().getBookingSet()
                .stream().map(BookingDto::new).collect(Collectors.toList());
    }

    @GetMapping(path = "{id}/dates")
    public List<DatesDto> getCarDates(@PathVariable("id") @NotNull int id){

        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        return car.getDates()
                .stream().map(DatesDto::new).collect(Collectors.toList());
    }

    @GetMapping(path = "/filter/make/{make}")
    public List<CarDto> getByMake(@PathVariable("make")String make){

        return carRepository.findByMake(make)
                .stream().map(CarDto::new).collect(Collectors.toList());
    }

    @GetMapping(path = "/filter/{make}/{model}")
    public List<CarDto> findByMakeAndModel(@PathVariable("make")String make, @PathVariable("model")String model){

        return carRepository.findByMakeAndModel(make, model)
                .stream().map(CarDto::new).collect(Collectors.toList());
    }

    @GetMapping(path = "/filterPrice/{from}/{to}")
    public List<CarDto> findByPricePerDay(@PathVariable("from")Integer from, @PathVariable("to")Integer to){

        return carRepository.findByPricePerDayBetween(from, to)
                .stream().map(CarDto::new).collect(Collectors.toList());
    }
}
