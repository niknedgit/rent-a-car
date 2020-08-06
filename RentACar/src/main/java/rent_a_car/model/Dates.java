package rent_a_car.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import sun.util.calendar.BaseCalendar;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "dates", schema = "public")
public class Dates {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dates_id")
    private Integer id;

    @Column(name = "date_from")
    private String date_from;

    @Column(name = "date_to")
    private String date_to;

    @Column(name = "car_id")
    private Integer car_id;

    /*@ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;*/

    public Integer getCar_id() {
        return car_id;
    }

    public void setCar_id(Integer car_id) {
        this.car_id = car_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate_from() {
        return date_from;
    }

    public void setDate_from(String date_from) {
        this.date_from = date_from;
    }

    public String getDate_to() {
        return date_to;
    }

    public void setDate_to(String date_to) {
        this.date_to = date_to;
    }
}
