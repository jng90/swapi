package service;

import model.Planet;

import java.util.Optional;

public interface SWPlanetService {
    Optional<Planet> findById(int id);
}
