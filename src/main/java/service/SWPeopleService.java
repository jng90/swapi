package service;

import model.Person;

import java.util.List;
import java.util.Optional;

public interface SWPeopleService {
    Optional<Person> findById(int id);

    List<Person> findByHairColor(String color);

    int countFilmsByPersonID(int id);
}
