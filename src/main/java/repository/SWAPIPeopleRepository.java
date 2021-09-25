package repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Person;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

public class SWAPIPeopleRepository implements SWPeopleRepository{
    public static final ObjectMapper MAPPER = new ObjectMapper();
    private static final HttpClient client = HttpClient.newHttpClient();
    public static final String SWAPI_DEV_API_PEOPLE = "https://swapi.dev/api/people/";
    @Override
    public List<Person> findAll() {
        return null;
    }

    private Map<Integer, Person> cache = new HashMap<>();

    @Override
    public Optional<Person> findById(int id) {
//        if (cache.containsKey(id)){
//            return Optional.of(cache.get(id));
//        }
//        else {
//            try {
//                getPersonFromApi(SWAPI_DEV_API_PEOPLE + id, id);
//            } catch (URISyntaxException e) {
//                System.err.println(e.getMessage());
//            }
//            return Optional.empty();
//        }
        if (cache.containsKey(id)){
            return Optional.of(cache.get(id));
        } else{
            try {
                getObjectFromApi(SWAPI_DEV_API_PEOPLE + id, id, body -> {
                    try {
                        Person person = MAPPER.readValue(body, Person.class);
                        cache.put(id, person);
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
    private void getPersonFromApi(String url, int id) throws URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .whenCompleteAsync((response, exeption) -> {
                    if (exeption != null){
                        System.err.println("Problem z odpowiedzią z serwera!");
                    }
                    String json = response.body();
                    ObjectMapper mapper = new ObjectMapper();
                    try {

                        Person person = mapper.readValue(json, Person.class);
                        cache.put(id, person);
                    } catch (JsonProcessingException e) {
                        System.err.println("Problem z parsowanie JSON!");
                        System.err.println(e.getMessage());
                    }
                });
    }
    private void getObjectFromApi(String url, int id, Consumer<String> consumer) throws URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .whenCompleteAsync((response, exeption) -> {
                    if (exeption != null){
                        System.err.println("Problem z odpowiedzią z serwera!");
                    }
                    consumer.accept(response.body());
                });
    }
}