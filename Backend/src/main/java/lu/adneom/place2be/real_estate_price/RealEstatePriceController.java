package lu.adneom.place2be.real_estate_price;

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
@RequestMapping("/real_estate_price")
public class RealEstatePriceController {

    private RealEstatePriceService realEstatePriceService;

    @Autowired
    public RealEstatePriceController(RealEstatePriceService realEstatePriceService) {
        this.realEstatePriceService = realEstatePriceService;
    }

    @ApiOperation(value = "Get all real estate price", tags = "real Estate Price")
    @GetMapping(path = "/all")
    public ResponseEntity<List<RealEstatePrice>> getAll() {
        return ResponseEntity.ok(realEstatePriceService.getAll());
    }

}
