package lu.adneom.place2be.population;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class PopulationController {

    private PopulationService populationService;

    @Autowired
    public PopulationController(PopulationService populationService) {
        this.populationService = populationService;
    }

    @ApiOperation(value = "Get all population", tags = "population")
    @GetMapping(path = "/population/all")
    public ResponseEntity<List<Population>> getAll() {
        return ResponseEntity.ok(populationService.getAll());
    }

}
