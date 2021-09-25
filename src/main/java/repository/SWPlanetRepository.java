package repository;

import model.Planet;

import java.util.List;
import java.util.Optional;

public interface SWPlanetRepository {
    List<Planet> findAll();
    Optional<Planet> findById (int id);
}
