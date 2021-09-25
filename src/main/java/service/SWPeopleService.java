package service;

import model.Person;

import java.util.Optional;

public interface SWPeopleService {
    Optional<Person> findById(int id);
}
