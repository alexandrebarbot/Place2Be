package lu.adneom.place2be.school;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/school")
public class SchoolController {

    private SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @ApiOperation(value = "Get all school", tags = "school")
    @GetMapping(path = "/all")
    public ResponseEntity<List<School>> getAll() {
        return ResponseEntity.ok(schoolService.getAll());
    }

    @ApiOperation(value = "Get school around the area", tags = "school")
    @GetMapping(path = "/around")
    public ResponseEntity<List<School>> getAround(@RequestParam float longitude, @RequestParam float latitude,
                                                  @RequestParam float radius) {
        return ResponseEntity.ok(schoolService.getAround(latitude, longitude, radius));
    }
}
