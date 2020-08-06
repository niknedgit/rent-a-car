package rent_a_car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rent_a_car.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    //List<Answer> findByQuestionId(Long questionId);
}
