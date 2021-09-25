package repository;

import model.Person;

import java.util.List;
import java.util.Optional;

public interface SWPeopleRepository {
    List<Person> findAll();
    Optional<Person> findById(int id);
}
