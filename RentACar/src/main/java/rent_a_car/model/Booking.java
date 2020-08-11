package rent_a_car.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue
    @Column(name = "booking_id")
    private Integer id;

    @Column(name = "pick_up_date")
    private LocalDate pickUpDate;

    @Column(name = "drop_off_date")
    private LocalDate dropOffDate;

    @Column(name = "price")
    private float price;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Booking(){}

    public Booking(LocalDate pickUpDate, LocalDate dropOffDate, float price, Car car, Account account) {
        this.pickUpDate = pickUpDate;
        this.dropOffDate = dropOffDate;
        this.price = price;
        this.car = car;
        this.account = account;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(LocalDate pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public LocalDate getDropOffDate() {
        return dropOffDate;
    }

    public void setDropOffDate(LocalDate dropOffDate) {
        this.dropOffDate = dropOffDate;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
