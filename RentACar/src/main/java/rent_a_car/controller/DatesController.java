package rent_a_car.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rent_a_car.exception.ResourceNotFoundException;
import rent_a_car.model.Car;
import rent_a_car.model.Dates;
import rent_a_car.repository.CarRepository;
import rent_a_car.repository.DatesRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dates")
public class DatesController {

    @Autowired
    private DatesRepository datesRepository;
    @Autowired
    private CarRepository carRepository;

    @GetMapping(path = "/all")
    public List<Dates> getAll(){
        return datesRepository.findAll();
    }

    @GetMapping(path = "{id}")
    public Dates getDates(@PathVariable("id") @NotNull int id){
        return datesRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Dates not found with id " + id));
    }

    @PostMapping
    public Dates addDates(@NotNull @Valid @RequestBody Dates dates){
        return datesRepository.save(dates);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteDates(@PathVariable int id) {
        return datesRepository.findById(id)
                .map(dates -> {
                    datesRepository.delete(dates);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Dates not found with id " + id));
    }

    @GetMapping(path = "/{id}/car")
    public Optional<Car> getDatesCar(@PathVariable("id") @NotNull int id) {
        return datesRepository.findById(id)
                .map(dates->{
                    return carRepository.findById(dates.getCar_id());
                }).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
    }
}
