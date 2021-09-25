package app;

import model.Person;
import repository.SWAPIPeopleRepository;
import repository.SWPeopleRepository;
import service.SWAPIPeopleService;
import service.SWPeopleService;

import java.util.Optional;
import java.util.Scanner;

public class CommandLineSWAPIApp {

    public static final Scanner scanner = new Scanner(System.in);
    public static final SWPeopleRepository peopleRepository = new SWAPIPeopleRepository();
    public static final SWPeopleService peopleService = new SWAPIPeopleService(peopleRepository);


    public static void main(String[] args) {
        while (true) {
            System.out.println("Podaj id osoby: ");
            int id = scanner.nextInt();
            Optional<Person> person = peopleService.findById(id);
            if (person.isPresent()) {
                System.out.println(person.get());
            } else {
                System.out.println("Na razie brak informacji o tej osobie lub nie ma jej");
            }
            scanner.nextLine();
        }
    }
}
