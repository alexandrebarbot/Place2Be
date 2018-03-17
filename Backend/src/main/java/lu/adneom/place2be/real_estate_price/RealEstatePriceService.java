package lu.adneom.place2be.real_estate_price;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RealEstatePriceService {

    private RealEstatePriceRepository realEstatePriceRepository;

    public RealEstatePriceService() {

    }

    @Autowired
    public RealEstatePriceService(RealEstatePriceRepository realEstatePriceRepository) {
        this.realEstatePriceRepository = realEstatePriceRepository;
    }

    public List<RealEstatePrice> getAll() {
        return (List<RealEstatePrice>) realEstatePriceRepository.findAll();
    }
}
