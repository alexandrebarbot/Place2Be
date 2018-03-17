package lu.adneom.place2be.bus_stop;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/bus_stop")
public class BusStopController {

    private BusStopService busStopService;

    @Autowired
    public BusStopController(BusStopService busStopService) {
        this.busStopService = busStopService;
    }

    @ApiOperation(value = "Get all bus stop", tags = "bus_stop")
    @GetMapping(path = "/all")
    public ResponseEntity<List<BusStop>> getAll() {
        return ResponseEntity.ok(busStopService.getAll());
    }

    @ApiOperation(value = "Get bus stop around he area", tags = "bus_stop")
    @GetMapping(path = "/around")
    public ResponseEntity<List<BusStop>> getAround(@RequestParam float longitude, @RequestParam float latitude,
                                                   @RequestParam float radius) {
        return ResponseEntity.ok(busStopService.getAround(latitude, longitude, radius));
    }

}
