package app;

import model.Person;
import repository.SWAPIPeopleRepository;
import repository.SWPeopleRepository;
import service.SWAPIPeopleService;
import service.SWPeopleService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CommandLineMenuApp {
    public static final SWPeopleRepository peopleRepository = new SWAPIPeopleRepository();
    public static final SWPeopleService peopleService = new SWAPIPeopleService(peopleRepository);
    public static Menu menu = new Menu(List.of(

            new MenuItem("Wyszukaj osobę", () -> {
                Optional<Person> byId = peopleService.findById(2);
                System.out.println(byId);
            }),
            new MenuItem("Wyszukaj planetę", () -> {
                System.out.println("Szukanie planety");
            }),
            new MenuItem("Nowa funkcja", () -> {
                System.out.println("Wykonanie nowej funkcji");
            }),
            new MenuItem("Koniec", () -> {
                System.exit(0);
            })
    ));
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        while(true) {
            menu.print();
            int option = scanner.nextInt();
            menu.executeOption(option);
        }
    }
}