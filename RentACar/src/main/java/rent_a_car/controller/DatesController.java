package rent_a_car.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rent_a_car.dto.CarDto;
import rent_a_car.dto.DatesDto;
import rent_a_car.exception.BadRequestException;
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
import java.util.stream.Collectors;

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
    public DatesDto getDates(@PathVariable("id") @NotNull int id){

        Optional<Dates> dates = datesRepository.findById(id);

        dates
                .orElseThrow(()->new ResourceNotFoundException("Dates not found with id " + id));
        return dates.map(DatesDto::new).get();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(path = "/secured/add")
    public DatesDto addDates(@NotNull @Valid @RequestBody DatesDto datesDto){

        if(datesDto.getDateFrom().isAfter(datesDto.getDateTo()))
            throw new BadRequestException("dateFrom must be before dateTo");

        Optional<Car> optionalCar = carRepository.findById(datesDto.getCarId());
        optionalCar
                .orElseThrow(()->new ResourceNotFoundException("Car not found with id " + datesDto.getCarId()));

        Dates dates = new Dates(datesDto.getDateFrom(), datesDto.getDateTo(), datesDto.getCarId());
        return new DatesDto(datesRepository.save(dates));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(path = "/secured/{id}")
    public ResponseEntity<?> deleteDates(@PathVariable int id) {

        return datesRepository.findById(id)
                .map(dates -> {
                    datesRepository.delete(dates);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Dates not found with id " + id));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(path = "/secured/{datesId}")
    public DatesDto updateDates(@PathVariable int datesId,
                             @Valid @RequestBody DatesDto datesRequest) {

        return datesRepository.findById(datesId)
                .map(dates -> {
                    dates.setDateFrom(datesRequest.getDateFrom());
                    dates.setDateTo(datesRequest.getDateTo());
                    dates.setCarId(datesRequest.getCarId());
                    return new DatesDto(datesRepository.save(dates));
                }).orElseThrow(() -> new ResourceNotFoundException("Dates not found with id " + datesId));
    }

    @GetMapping(path = "/{id}/car")
    public CarDto getDatesCar(@PathVariable("id") @NotNull int id) {

        Dates dates = datesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dates not found with id " + id));

        Optional<Car> car = carRepository.findById(dates.getCarId());
        car
                .orElseThrow(()->new ResourceNotFoundException("Car not found"));
        return car.map(CarDto::new).get();
    }

    @GetMapping(path = "/filter/{dateFrom}/{dateTo}")
    public List<CarDto> filter(@PathVariable("dateFrom") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFrom,
                                      @PathVariable("dateTo") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTo){

        List<Integer> car_ids = datesRepository.filter(dateFrom, dateTo);
        List<Optional<Car>> cars = new ArrayList<>();

        for (int i: car_ids) {
            Optional<Car> car = carRepository.findById(i);
            if(car.isPresent())
                cars.add(car);
        }
        return cars.stream().map(car -> new CarDto(car.get())).collect(Collectors.toList());
    }
}
