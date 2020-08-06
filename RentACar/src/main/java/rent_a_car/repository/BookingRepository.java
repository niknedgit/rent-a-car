package rent_a_car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rent_a_car.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
