package lu.adneom.place2be.rank;

import lu.adneom.place2be.bus_stop.BusStop;
import lu.adneom.place2be.bus_stop.BusStopService;
import lu.adneom.place2be.school.School;
import lu.adneom.place2be.school.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RankService {

    private SchoolService schoolService;
    private BusStopService busStopService;

    public RankService() {

    }

    @Autowired
    public RankService(SchoolService schoolService, BusStopService busStopService) {
        this.schoolService = schoolService;
        this.busStopService = busStopService;
    }

    public Rank calculate(float longitude, float latitude, float radius) {
        Rank rank = new Rank();
        rank.setSchools(getSchool(longitude, latitude, radius));
        rank.setBusStops(getBusStop(longitude, latitude, radius));
        return rank;
    }

    private Map<School, Double> getSchool(float longitude, float latitude, float radius) {
        List<School> schools = schoolService.getInArea(longitude, latitude, radius);
        Map<School, Double> distanceBetweenLocationAndSchools = new HashMap<>();
        schools.stream().forEach(school -> {
            distanceBetweenLocationAndSchools.put(school, distance(latitude, longitude, school
                    .getLatitude(), school.getLongitude()));
        });
        return distanceBetweenLocationAndSchools;
    }

    private Map<BusStop, Double> getBusStop(float longitude, float latitude, float radius) {
        List<BusStop> busStops = busStopService.getAround(latitude, longitude, radius);
        Map<BusStop, Double> distanceBetweenLocationAndBusStop = new HashMap<>();
        busStops.stream().forEach(busStop -> {
            distanceBetweenLocationAndBusStop.put(busStop, distance(latitude, longitude, busStop
                    .getLatitude(), busStop.getLongitude()));
        });
        return distanceBetweenLocationAndBusStop;
    }

    private static double distance(float lat1, float lon1, float lat2, float lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad
                (lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        return (dist);
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
