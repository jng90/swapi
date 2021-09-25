package service;

import model.Person;
import repository.SWPeopleRepository;

import java.util.Optional;

public class SWAPIPeopleService implements SWPeopleService{

    private final SWPeopleRepository people;

    public SWAPIPeopleService(SWPeopleRepository people) {
        this.people = people;
    }

    public SWAPIPeopleService() {
        people = null;
    }

    @Override
    public Optional<Person> findById(int id) {
        return people.findById(id);
    }

}