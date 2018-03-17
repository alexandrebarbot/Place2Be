package lu.adneom.place2be.parking_relais;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingRelaisRepository extends CrudRepository<ParkingRelais, Long> {
    List<ParkingRelais> findByLongitudeBetweenAndLatitudeBetween(float longitudeMin, float longitudeMax, float
            latitudeMin, float latitudeMax);
}
