package lu.adneom.place2be.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Math.PI;
import static java.lang.Math.cos;

@Service
public class SchoolService {

    private SchoolRepository schoolRepository;

    public SchoolService() { }

    @Autowired
    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public List<School> getAll() {
        return (List<School>) schoolRepository.findAll();
    }

    public List<School> getInArea(float longitude, float latitude, float radius) {
        radius *= 1000;
        float latitudeMin = (float) (latitude - (radius / 6378137) * (180 / PI));
        float latitudeMax = (float) (latitude + (radius / 6378137) * (180 / PI));
        float longitudeMin = (float) (longitude - (radius / (6378137 * cos(PI * latitude / 180)) * (180 / PI)));
        float longitudeMax = (float) (longitude + (radius / (6378137 * cos(PI * latitude / 180)) * (180 / PI)));
        return schoolRepository.findByLongitudeBetweenAndLatitudeBetween(longitudeMin, longitudeMax, latitudeMin,
                latitudeMax);
    }
}
