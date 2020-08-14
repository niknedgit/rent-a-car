package rent_a_car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rent_a_car.model.Car;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    List<Car> findByMake(String make);
    List<Car> findByMakeAndModel(String make, String model);
    List<Car> findByPricePerDayBetween(float from, float to);
}
