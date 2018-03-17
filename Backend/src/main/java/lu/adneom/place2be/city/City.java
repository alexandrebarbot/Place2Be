package lu.adneom.place2be.city;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "cities")
public class City implements Serializable {

    @Id
    private int id;
    private String name;
    private float longitude;
    private float latitude;

}
