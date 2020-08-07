package rent_a_car.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rent_a_car.exception.ResourceNotFoundException;
import rent_a_car.model.Car;
import rent_a_car.model.Dates;
import rent_a_car.repository.CarRepository;
import rent_a_car.repository.DatesRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
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

    @PutMapping(path = "{datesId}")
    public Dates updateDates(@PathVariable int datesId,
                             @Valid @RequestBody Dates datesRequest) {
        return datesRepository.findById(datesId)
                .map(dates -> {
                    dates.setDate_from(datesRequest.getDate_from());
                    dates.setDate_to(datesRequest.getDate_to());
                    dates.setCar_id(datesRequest.getCar_id());
                    return datesRepository.save(dates);
                }).orElseThrow(() -> new ResourceNotFoundException("Dates not found with id " + datesId));
    }

    @GetMapping(path = "/{id}/car")
    public Optional<Car> getDatesCar(@PathVariable("id") @NotNull int id) {
        Dates dates= datesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        return carRepository.findById(dates.getCar_id());
    }

    @GetMapping(path = "/filter/{dateFrom}/{dateTo}")
    public List<Optional<Car>> filter(@PathVariable("dateFrom") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFrom,
                                      @PathVariable("dateTo") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTo){

        List<Integer> car_ids = datesRepository.filter(dateFrom, dateTo);
        List<Optional<Car>> cars = new ArrayList<>();

        if(car_ids.size()!=0) {
            for (int i: car_ids) {
                int j=car_ids.get(i);
                Optional<Car> car = carRepository.findById(j);
                if(car.isPresent())
                    cars.add(car);
            }
        }
        return cars;
    }
}
