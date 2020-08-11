package rent_a_car.dto;

import rent_a_car.model.Booking;

import java.time.LocalDate;

public class BookingDto {

    private Integer id;
    private LocalDate pickUpDate;
    private LocalDate dropOffDate;
    private float price;
    private Integer carId;
    private Integer accountId;

    public BookingDto(){}

    public BookingDto(Booking booking){
        this.id = booking.getId();
        this.pickUpDate = booking.getPickUpDate();
        this.dropOffDate = booking.getDropOffDate();
        this.price = booking.getPrice();
        this.carId = booking.getCar().getId();
        this.accountId = booking.getAccount().getId();
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getPickUpDate() {
        return pickUpDate;
    }

    public LocalDate getDropOffDate() {
        return dropOffDate;
    }

    public float getPrice() {
        return price;
    }

    public Integer getCarId() {
        return carId;
    }

    public Integer getAccountId() {
        return accountId;
    }
}
