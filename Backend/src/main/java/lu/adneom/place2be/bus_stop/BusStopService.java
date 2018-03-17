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
        radius *= 1000;
        float latitudeMin = (float) (latitude - (radius / 6378137) * (180 / PI));
        float latitudeMax = (float) (latitude + (radius / 6378137) * (180 / PI));
        float longitudeMin = (float) (longitude - (radius / (6378137 * cos(PI * latitude / 180)) * (180 / PI)));
        float longitudeMax = (float) (longitude + (radius / (6378137 * cos(PI * latitude / 180)) * (180 / PI)));

        return busStopRepository.findByLatitudeBetweenAndLongitudeBetween(latitudeMin,
                latitudeMax, longitudeMin, longitudeMax);
    }
}
