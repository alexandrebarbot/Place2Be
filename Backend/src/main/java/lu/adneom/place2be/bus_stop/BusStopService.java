package lu.adneom.place2be.bus_stop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Math.PI;
import static java.lang.Math.cos;

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
        float latitudeMin = (float) (latitude - (180 / PI) * (radius / 6378137));
        float latitudeMax = (float) (latitude + (180 / PI) * (radius / 6378137));
        float longitudeMin = (float) (longitude - (180 / PI) * (radius / 6378137) / cos(latitude));
        float longitudeMax = (float) (longitude + (180 / PI) * (radius / 6378137) / cos(latitude));

        return busStopRepository.findByLatitudeBetweenAndLongitudeBetween(latitudeMin,
                latitudeMax, longitudeMin, longitudeMax);
    }
}
