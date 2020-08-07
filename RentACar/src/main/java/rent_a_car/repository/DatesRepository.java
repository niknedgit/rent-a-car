package rent_a_car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rent_a_car.model.Dates;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DatesRepository extends JpaRepository<Dates, Integer> {

    @Query(value = "SELECT DISTINCT car_id FROM dates d WHERE d.date_from >= :from AND d.date_to <= :to", nativeQuery = true)
    List<Integer> filter(@Param("from") LocalDate dateFrom, @Param("to") LocalDate dateTo);
}
