package lu.adneom.place2be.parking_relais;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Math.PI;
import static java.lang.Math.cos;

@Service
public class ParkingRelaisService {

    private ParkingRelaisRepository parkingRelaisRepository;

    public ParkingRelaisService() {

    }

    @Autowired
    public ParkingRelaisService(ParkingRelaisRepository parkingRelaisRepository) {
        this.parkingRelaisRepository = parkingRelaisRepository;
    }

    public List<ParkingRelais> getAll() {
        return (List<ParkingRelais>) parkingRelaisRepository.findAll();
    }

    public List<ParkingRelais> getAround(float longitude, float latitude, float radius) {
        radius *= 1000;
        float latitudeMin = (float) (latitude - (radius / 6378137) * (180 / PI));
        float latitudeMax = (float) (latitude + (radius / 6378137) * (180 / PI));
        float longitudeMin = (float) (longitude - (radius / (6378137 * cos(PI * latitude / 180)) * (180 / PI)));
        float longitudeMax = (float) (longitude + (radius / (6378137 * cos(PI * latitude / 180)) * (180 / PI)));
        return parkingRelaisRepository.findByLongitudeBetweenAndLatitudeBetween(longitudeMin, longitudeMax, latitudeMin,
                latitudeMax);
    }

}
