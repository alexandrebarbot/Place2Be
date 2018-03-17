package lu.adneom.place2be.parking_relais;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRelaisRepository extends CrudRepository<ParkingRelais, Long> {
}
