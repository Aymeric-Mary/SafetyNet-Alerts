package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.FireStation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FireStationRepository extends AbstractRepository {

    private List<FireStation> fireStations;

    public FireStationRepository(){
        this.fireStations = data.getFirestations();
    }

    public List<FireStation> findAll() {
        return fireStations;
    }

    public List<FireStation> findByStation(Integer station){
        return fireStations
                .stream()
                .filter(fireStation -> fireStation.getStation().equals(station))
                .toList();
    }

}