package rent_a_car.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "dates", schema = "public")
public class Dates {
    @Id
    @GeneratedValue
    @Column(name = "dates_id", nullable = false)
    private Integer id;

    @Column(name = "date_from")
    private LocalDate dateFrom;

    @Column(name = "date_to")
    private LocalDate dateTo;

    @Column(name = "car_id")
    private Integer carId;

    /*@ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;*/

    public Dates(){}

    public Dates(LocalDate dateFrom, LocalDate dateTo, Integer carId) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.carId = carId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }
}
