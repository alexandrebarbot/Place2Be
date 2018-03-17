package lu.adneom.place2be.population;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PopulationService {

    private PopulationRepository populationRepository;

    public PopulationService() {

    }

    @Autowired
    public PopulationService(PopulationRepository populationRepository) {
        this.populationRepository = populationRepository;
    }

    public List<Population> getAll() {
        return (List<Population>) populationRepository.findAll();
    }
}
