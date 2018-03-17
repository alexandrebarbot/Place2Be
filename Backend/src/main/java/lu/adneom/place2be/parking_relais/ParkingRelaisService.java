package lu.adneom.place2be.parking_relais;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
