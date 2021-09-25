package service;

import model.Planet;
import repository.SWPlanetRepository;

import java.util.Optional;

public class SWAPIPlanetService implements SWPlanetService{
    private final SWPlanetRepository planet;

    public SWAPIPlanetService(SWPlanetRepository planet) {
        this.planet = planet;
    }
    public SWAPIPlanetService() {
        planet = null;
    }
    @Override
    public Optional<Planet> findById(int id) {
        return planet.findById(id);
    }
}

