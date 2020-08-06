package rent_a_car.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "car", schema = "public")
public class Car {

    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_generator")
    //@SequenceGenerator(name="car_generator", sequenceName = "car_seq", initialValue=50)
    @Column(name = "car_id")
    private Integer id;

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Column(name = "image_url")
    private String image_url;

    @Column(name = "price_per_day")
    private int price_per_day;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "car_id")
    private List<Dates> dates;

    public Car() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getPrice_per_day() {
        return price_per_day;
    }

    public void setPrice_per_day(int price_per_day) {
        this.price_per_day = price_per_day;
    }

    public List<Dates> getDates() {
        return dates;
    }

    public void setDates(List<Dates> dates) {
        this.dates = dates;
    }
}
