package rent_a_car.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "car", schema = "public")
public class Car {

    @Id
    @GeneratedValue
    @Column(name = "car_id")
    private Integer id;

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "price_per_day")
    private int pricePerDay;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public List<Dates> getDates() {
        return dates;
    }

    public void setDates(List<Dates> dates) {
        this.dates = dates;
    }
}
