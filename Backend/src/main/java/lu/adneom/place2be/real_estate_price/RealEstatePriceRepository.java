package lu.adneom.place2be.real_estate_price;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealEstatePriceRepository extends CrudRepository<RealEstatePrice, Long> {
}
