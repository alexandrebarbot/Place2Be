package lu.adneom.place2be.parking_relais;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/parking_relais")
public class ParkingRelaisController {

    private ParkingRelaisService parkingRelaisRepository;

    @Autowired
    public ParkingRelaisController(ParkingRelaisService parkingRelaisRepository) {
        this.parkingRelaisRepository = parkingRelaisRepository;
    }

    @ApiOperation(value = "Get all parking relais", tags = "Parking Relais")
    @GetMapping(path = "/all")
    public ResponseEntity<List<ParkingRelais>> getAll() {
        return ResponseEntity.ok(parkingRelaisRepository.getAll());
    }

}
