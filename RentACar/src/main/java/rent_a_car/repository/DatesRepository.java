package rent_a_car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rent_a_car.model.Dates;

import java.util.List;

@Repository
public interface DatesRepository extends JpaRepository<Dates, Integer> {

}
