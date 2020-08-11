package rent_a_car.dto;

import rent_a_car.model.Dates;

import java.time.LocalDate;

public class DatesDto {

    private Integer id;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Integer carId;

    public DatesDto(){}

    public DatesDto(Dates dates){
        this.id = dates.getId();
        this.dateFrom = dates.getDateFrom();
        this.dateTo = dates.getDateTo();
        this.carId = dates.getCarId();
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public Integer getCarId() {
        return carId;
    }
}
