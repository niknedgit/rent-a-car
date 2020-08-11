package rent_a_car.dto;

import rent_a_car.model.Car;

import java.util.List;
import java.util.stream.Collectors;

public class CarDto {

    private Integer id;
    private String make;
    private String model;
    private String imageUrl;
    private float pricePerDay;
    //private List<DatesDto> datesDto;

    public CarDto(){}

    public CarDto(Car car){
        this.id = car.getId();
        this.make = car.getMake();
        this.model = car.getModel();
        this.imageUrl = car.getImageUrl();
        this.pricePerDay = car.getPricePerDay();
        //this.datesDto = car.getDates()
                //.stream().map(dates -> new DatesDto(dates)).collect(Collectors.toList());
    }

    public Integer getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public float getPricePerDay() {
        return pricePerDay;
    }

//    public List<DatesDto> getDatesDto() {
//        return datesDto;
//    }
}
