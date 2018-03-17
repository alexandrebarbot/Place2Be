package lu.adneom.place2be.population;

import lombok.Data;
import lu.adneom.place2be.city.City;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "populations")
public class Population implements Serializable {

    @Id
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private City city;

    private int value;
    private int year;
}
