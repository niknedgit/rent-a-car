package rent_a_car.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
//@SequenceGenerator(name="seq2", initialValue=10, allocationSize=100)
@Table(name = "booking")
public class Booking {
    @Id
    //@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq2")
    //@Column(name = "booking_id", updatable = false, nullable = false)
    @Column(name = "booking_id")
    private Integer id;

    @Column(name = "pick_up_date")
    private LocalDate pick_up_date;

    @Column(name = "drop_off_date")
    private LocalDate drop_off_date;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", referencedColumnName = "car_id")
    private Car car;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getPick_up_date() {
        return pick_up_date;
    }

    public void setPick_up_date(LocalDate pick_up_date) {
        this.pick_up_date = pick_up_date;
    }

    public LocalDate getDrop_off_date() {
        return drop_off_date;
    }

    public void setDrop_off_date(LocalDate drop_off_date) {
        this.drop_off_date = drop_off_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
