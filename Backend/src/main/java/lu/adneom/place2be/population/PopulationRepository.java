package lu.adneom.place2be.population;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PopulationRepository extends CrudRepository<Population, Long> {
}
