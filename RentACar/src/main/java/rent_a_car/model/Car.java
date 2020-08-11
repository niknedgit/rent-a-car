package rent_a_car.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    private float pricePerDay;

    @OneToMany//(cascade=CascadeType.ALL)
    @JoinColumn(name = "car_id")
    private List<Dates> dates;

    @OneToMany(mappedBy = "car")
    private List<Booking> bookingSet;

    public Car() {
        dates = new ArrayList<>();
        bookingSet= new ArrayList<>();
    }

    public Car(String make, String model, String imageUrl, float pricePerDay) {
        this.make = make;
        this.model = model;
        this.imageUrl = imageUrl;
        this.pricePerDay = pricePerDay;
    }

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

    public float getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(float pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public List<Dates> getDates() {
        return dates;
    }

    public void setDates(List<Dates> dates) {
        this.dates = dates;
    }

    public List<Booking> getBookingSet() {
        return bookingSet;
    }

    public void setBookingSet(List<Booking> bookingSet) {
        this.bookingSet = bookingSet;
    }
}
