package com.safetynet.repository;

import com.safetynet.model.FireStation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FireStationRepository extends AbstractRepository {

    private List<FireStation> fireStations;

    public FireStationRepository() {
        this.fireStations = data.getFirestations();
    }

    public List<FireStation> findAll() {
        return fireStations;
    }

    public List<FireStation> findByStation(Integer station) {
        return fireStations
                .stream()
                .filter(fireStation -> fireStation.getStation().equals(station))
                .toList();
    }

    public Optional<FireStation> findByAddress(String address) {
        return fireStations
                .stream()
                .filter(fireStation -> address.equals(fireStation.getAddress()))
                .findFirst();
    }

    public List<FireStation> findByStations(List<Integer> stations) {
        return fireStations
                .stream()
                .filter(fireStation -> stations.contains(fireStation.getStation()))
                .toList();
    }

    public FireStation save(FireStation fireStation) {
        Optional<FireStation> existingFireStation = findByAddress(fireStation.getAddress());
        if (existingFireStation.isPresent()) {
            existingFireStation.get().setStation(fireStation.getStation());
            return existingFireStation.get();
        }
        fireStations.add(fireStation);
        return fireStation;
    }

    public void delete(FireStation fireStation) {
        fireStations.remove(fireStation);
    }
}