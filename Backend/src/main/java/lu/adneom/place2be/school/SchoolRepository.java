package lu.adneom.place2be.school;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolRepository extends CrudRepository<School, Long> {
    List<School> findByLongitudeBetweenAndLatitudeBetween(float longitudeMin, float longitudeMax, float latitudeMin,
                                                          float latitudeMax);
}
