package lu.adneom.place2be.rank;

import lombok.Data;
import lu.adneom.place2be.bus_stop.BusStop;
import lu.adneom.place2be.parking_relais.ParkingRelais;
import lu.adneom.place2be.school.School;

import java.io.Serializable;
import java.util.Map;

@Data
public class Rank implements Serializable {

    private Map<School, Double> schools;
    private Map<BusStop, Double> busStops;
    private Map<ParkingRelais, Double> parkingRelais;
}
