package app;

import model.Person;
import model.Planet;
import repository.SWAPIPeopleRepository;
import repository.SWAPIPlanetRepository;
import repository.SWPeopleRepository;
import repository.SWPlanetRepository;
import service.SWAPIPeopleService;
import service.SWAPIPlanetService;
import service.SWPeopleService;
import service.SWPlanetService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CommandLineSWAPIApp {

    public static final Scanner scanner = new Scanner(System.in);
    public static final SWPeopleRepository peopleRepository = new SWAPIPeopleRepository();
    public static final SWPeopleService peopleService = new SWAPIPeopleService(peopleRepository);
    public static final SWPlanetRepository planetRepository = new SWAPIPlanetRepository();
    public static final SWPlanetService planetService = new SWAPIPlanetService(planetRepository);


    public static void menu() {
        System.out.println("1) - Szukaj osoby: ");
        System.out.println("2) - Szukaj planety: ");
        System.out.println("3) - Wyswietl wszystkie osoby: ");
        System.out.println("4) - Wyszukaj osoby wg koloru wlosow: ");
        System.out.println("5) - Ilosc filmow z postacia: ");
        System.out.println("0) - Koniec: ");
    }

    public static void executeOption(int option) {
        switch (option) {
            case 1:
                findPerson();
                break;
            case 2:
                findPlanet();
                break;
            case 3:
                findPeople();
                break;
            case 4:
                findByHairColor();
                break;
            case 5:
                countFilmsForPerson();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Nieprawidlowy numer opcji!");
        }
    }

    public static void countFilmsForPerson() {
        System.out.println("Podaj id osoby: ");
        int id = scanner.nextInt();
        int count = peopleService.countFilmsByPersonID(id);
        System.out.println("Znaleziona ilosc filmow dla tej postaci: " + count);
    }

    public static void findPeople() {
        System.out.println(peopleRepository.findAll());
    }

    public static void findByHairColor() {
        System.out.println("Wpisz kolor wlosow: ");
        String hairColor = scanner.next();
        scanner.nextLine();
        List<Person> people = peopleService.findByHairColor(hairColor);
        System.out.println("Lista osob o podanym kolorze wlosow: ");
        System.out.println(people);
    }

    public static void findPerson() {
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

    public static void findPlanet() {
        System.out.println("Podaj id planety: ");
        int id = scanner.nextInt();
        Optional<Planet> planet = planetService.findById(id);
        if (planet.isPresent()) {
            System.out.println(planet.get());
        } else {
            System.out.println("Na razie brak informacji o tej osobie lub nie ma jej");
        }
        scanner.nextLine();
    }

    public static void main(String[] args) {
        while (true) {
            menu();
            int option = scanner.nextInt();
            scanner.nextLine();
            executeOption(option);

        }
    }
}
