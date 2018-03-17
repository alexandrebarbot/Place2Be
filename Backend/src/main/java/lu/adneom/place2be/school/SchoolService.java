package lu.adneom.place2be.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {

    private SchoolRepository schoolRepository;

    public SchoolService() {

    }

    @Autowired
    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public List<School> getAll() {
        return (List<School>) schoolRepository.findAll();
    }
}
