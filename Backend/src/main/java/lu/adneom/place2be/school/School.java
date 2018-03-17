package lu.adneom.place2be.school;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "schools")
public class School implements Serializable {

    @Id
    private int id;
    private String name;
    private float longitude;
    private float latitude;
}

