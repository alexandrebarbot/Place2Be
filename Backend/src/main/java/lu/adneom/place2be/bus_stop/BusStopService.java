package lu.adneom.place2be.bus_stop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusStopService {

    private BusStopRepository busStopRepository;

    public BusStopService() { }

    @Autowired
    public BusStopService(BusStopRepository busStopRepository) {
        this.busStopRepository = busStopRepository;
    }

    public List<BusStop> getAll() {
        return (List<BusStop>) busStopRepository.findAll();
    }

    public List<BusStop> getAround(float latitude, float longitude, float radius) {
        return busStopRepository.findByLatitudeBetweenAndLongitudeBetween(latitude - radius,
                latitude + radius, longitude - radius, longitude + radius);
    }
}
