package repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Planet;

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


public class SWAPIPlanetRepository implements SWPlanetRepository {
    public static final ObjectMapper MAPPER = new ObjectMapper();
    private static final HttpClient client = HttpClient.newHttpClient();
    public static final String SWAPI_DEV_API_PLANETS = "https://swapi.dev/api/planets/";
    private Map<Integer, Planet> cache = new HashMap<>();

    @Override
    public List<Planet> findAll() {
        return null;
    }

    @Override
    public Optional<Planet> findById(int id) {
        if (cache.containsKey(id)) {
            return Optional.of(cache.get(id));
        } else {
            try {
                getObjectFromApi(SWAPI_DEV_API_PLANETS + id, id, body -> {
                    try {
                        Planet planet = MAPPER.readValue(body, Planet.class);
                        cache.put(id, planet);
                        System.out.println("Szukana planeta jest juz dostepna, wybierz ponownie: ");
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

