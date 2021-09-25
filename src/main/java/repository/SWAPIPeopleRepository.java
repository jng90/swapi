package repository;

import api.ApiHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Person;
import model.Persons;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.function.Consumer;

public class SWAPIPeopleRepository implements SWPeopleRepository {
    public static final ObjectMapper MAPPER = new ObjectMapper();
    private static final HttpClient client = HttpClient.newHttpClient();
    public static final String SWAPI_DEV_API_PEOPLE = "https://swapi.dev/api/people/";
    private Map<Integer, Person> cache = new HashMap<>();
    private List<Person> personsList = new ArrayList<>();

    @Override
    public List<Person> findAll() {
        if (personsList.isEmpty()) {
            try {
                ApiHelper.getObjectFromApi(SWAPI_DEV_API_PEOPLE, 0, body -> {
                    try {
                        Persons persons = MAPPER.readValue(body, Persons.class);
                        personsList.addAll(persons.getResults());
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                });
            } catch (URISyntaxException e) {
                System.err.println(e.getMessage());
            }
        }
            return personsList;
        }

    @Override
    public Optional<Person> findById(int id) {

        if (cache.containsKey(id)) {
            return Optional.of(cache.get(id));
        } else {
            try {
                getObjectFromApi(SWAPI_DEV_API_PEOPLE + id, id, body -> {
                    try {
                        Person person = MAPPER.readValue(body, Person.class);
                        cache.put(id, person);
                        System.out.println("Szukana osoba jest juz dostepna, wybierz ponownie: ");
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                });
            } catch (URISyntaxException e) {
                System.err.println(e.getMessage());
            }
            return Optional.empty();
        }
    }
    //pobieranie tylko person
//    private void getPersonFromApi(String url, int id) throws URISyntaxException {
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(new URI(url))
//                .GET()
//                .build();
//        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
//                .whenCompleteAsync((response, exeption) -> {
//                    if (exeption != null) {
//                        System.err.println("Problem z odpowiedzią z serwera!");
//                    }
//                    String json = response.body();
//                    ObjectMapper mapper = new ObjectMapper();
//                    try {
//
//                        Person person = mapper.readValue(json, Person.class);
//                        cache.put(id, person);
//                    } catch (JsonProcessingException e) {
//                        System.err.println("Problem z parsowanie JSON!");
//                        System.err.println(e.getMessage());
//                    }
//                });
//    }

    private void getObjectFromApi(String url, int id, Consumer<String> consumer) throws URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .whenCompleteAsync((response, exeption) -> {
                    if (exeption != null) {
                        System.err.println("Problem z odpowiedzią z serwera!");
                    }
                    consumer.accept(response.body());
                });
    }
}