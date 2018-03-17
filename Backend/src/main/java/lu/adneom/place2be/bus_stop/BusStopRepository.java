package lu.adneom.place2be.bus_stop;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusStopRepository extends CrudRepository<BusStop, Long> {
    List<BusStop> findByLatitudeBetweenAndLongitudeBetween(float latitudeMin, float latitudeMax, float longitudeMin,
                                                           float logitudeMax);
}
