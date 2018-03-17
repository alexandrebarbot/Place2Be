package lu.adneom.place2be.parking_relais;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/parking_relais")
public class ParkingRelaisController {

    private ParkingRelaisService parkingRelaisService;

    @Autowired
    public ParkingRelaisController(ParkingRelaisService parkingRelaisService) {
        this.parkingRelaisService = parkingRelaisService;
    }

    @ApiOperation(value = "Get all parking relais", tags = "parking Relais")
    @GetMapping(path = "/all")
    public ResponseEntity<List<ParkingRelais>> getAll() {
        return ResponseEntity.ok(parkingRelaisService.getAll());
    }

    @ApiOperation(value = "Get parking relais around the area", tags = "parking Relais")
    @GetMapping(path = "/around")
    public ResponseEntity<List<ParkingRelais>> getAround(@RequestParam float longitude, @RequestParam float latitude,
                                                         @RequestParam float radius) {
        return ResponseEntity.ok(parkingRelaisService.getAround(latitude, longitude, radius));
    }

}
