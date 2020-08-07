package rent_a_car.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rent_a_car.model.Car;
import rent_a_car.exception.ResourceNotFoundException;
import rent_a_car.model.Dates;
import rent_a_car.repository.CarRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/car")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping(path = "/all")
    public List<Car> getAll(){
        return carRepository.findAll();
    }

    @GetMapping(path = "{id}")
    public Car getCar(@PathVariable("id") @NotNull int id){
        return carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with id " + id));
    }

    @PostMapping
    public Car addCar(@NotNull @Valid @RequestBody Car car){
        return carRepository.save(car);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteCar(@PathVariable int id) {
        return carRepository.findById(id)
                .map(car -> {
                    carRepository.delete(car);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Car not found with id " + id));
    }

    @PutMapping(path = "{carId}")
    public Car updateCar(@PathVariable int carId,
                                   @Valid @RequestBody Car carRequest) {
        return carRepository.findById(carId)
                .map(car -> {
                    car.setImageUrl(carRequest.getImageUrl());
                    car.setMake(carRequest.getMake());
                    car.setModel(carRequest.getModel());
                    car.setPricePerDay(carRequest.getPricePerDay());
                    return carRepository.save(car);
                }).orElseThrow(() -> new ResourceNotFoundException("Car not found with id " + carId));
    }

    @GetMapping(path = "{id}/dates")
    public List<Dates> getCarDates(@PathVariable("id") @NotNull int id){
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        return car.getDates();
    }

    @GetMapping(path = "/filter/make/{make}")
    public List<Car> getByMake(@PathVariable("make")String make){
        return carRepository.findByMake(make);
    }

    @GetMapping(path = "/filter/{make}/{model}")
    public List<Car> findByMakeAndModel(@PathVariable("make")String make, @PathVariable("model")String model){
        return carRepository.findByMakeAndModel(make, model);
    }

    @GetMapping(path = "/filterPrice/{from}/{to}")
    public List<Car> findByPricePerDay(@PathVariable("from")Integer from, @PathVariable("to")Integer to){
        return carRepository.findByPricePerDayBetween(from, to);
    }
}
