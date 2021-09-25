package service;

import model.Person;
import repository.SWPeopleRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SWAPIPeopleService implements SWPeopleService {

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

    @Override
    public List<Person> findByHairColor(String color) {
        return null;
    }

    @Override
    public int countFilmsByPersonID(int id) {
        Optional<Person> person = people.findById(id);
        if (person.isPresent()) {
            return person.get().getFilms().size();
        } else {
            return 0;
        }
    }


}

