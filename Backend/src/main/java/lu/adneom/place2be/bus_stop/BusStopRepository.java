package lu.adneom.place2be.bus_stop;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusStopRepository extends CrudRepository<BusStop, Long> {
}
