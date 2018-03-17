package lu.adneom.place2be.bus_stop;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "bus_stop")
public class BusStop implements Serializable {

    @Id
    private int id;
    private String stop_name;
    private float longitude;
    private float latitude;
}
