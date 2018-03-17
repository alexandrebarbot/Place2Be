package lu.adneom.place2be.real_estate_price;

import lombok.Data;
import lu.adneom.place2be.city.City;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "real_estate_price")
public class RealEstatePrice implements Serializable {

    @Id
    private int id;
    private float moy_price_flat;
    private float moy_price_square_meter_flat;
    private float moy_price_house;
    private float moy_price_square_meter_house;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private City city;
}

