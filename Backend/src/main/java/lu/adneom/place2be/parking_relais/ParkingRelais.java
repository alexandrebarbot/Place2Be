package lu.adneom.place2be.parking_relais;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "parking_relais")
public class ParkingRelais implements Serializable {

    @Id
    private int id;
    private float longitude;
    private float latitude;
    private String localisation;
    private int places;
    private String connected_to;
}
