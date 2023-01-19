package com.safetynet.repository;

import com.safetynet.model.FireStation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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

    public Optional<FireStation> findByAdress(String address){
        return fireStations
                .stream()
                .filter(fireStation -> address.equals(fireStation.getAddress()))
                .findFirst();
    }

}