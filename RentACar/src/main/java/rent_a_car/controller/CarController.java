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
import java.util.Set;

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
                    car.setImage_url(carRequest.getImage_url());
                    car.setMake(carRequest.getMake());
                    car.setModel(carRequest.getModel());
                    car.setPrice_per_day(carRequest.getPrice_per_day());
                    return carRepository.save(car);
                }).orElseThrow(() -> new ResourceNotFoundException("Car not found with id " + carId));
    }

    @GetMapping(path = "{id}/dates")
    public List<Dates> getCarDates(@PathVariable("id") @NotNull int id){
        return carRepository.findById(id)
                .map(car->{
                    return car.getDates();
                }).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
    }
}
