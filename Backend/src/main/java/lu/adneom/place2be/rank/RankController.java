package lu.adneom.place2be.rank;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/rank")
public class RankController {

    private RankService rankService;

    @Autowired
    public RankController(RankService rankService) {
        this.rankService = rankService;
    }

    @ApiOperation(value = "Get all cities with their rank", tags = "rank")
    @GetMapping(path = "/byCoordinate")
    public ResponseEntity<Rank> getAll(@RequestParam float longitude, @RequestParam float latitude,
                                       @RequestParam float radius) {
        return ResponseEntity.ok(rankService.calculate(longitude, latitude, radius));
    }
}
