package org.example.network;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.example.exception.PokeAPIException;
import org.example.vo.Pokemon;
import org.example.vo.TipoAPI;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PokeAPI {
    private static PokeAPI  instance;

    private final HttpClient httpClient;

    private static final String url = "https://pokeapi.co/api/v2/";

    private PokeAPI() {
        httpClient = HttpClient.newHttpClient();
    }

    public static PokeAPI getInstance() {
        if (instance == null) {
            instance = new PokeAPI();
        }
        return instance;
    }

    private String call(String uri) throws PokeAPIException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + uri))
                .GET()
                .build();

        HttpResponse<String> response;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new PokeAPIException("PokeAPI", e.getMessage());
        }

        if (response.statusCode() < 200 && response.statusCode() > 300) {
            throw new PokeAPIException("PokeAPI", response.statusCode() + ": " + response.body());
        }
        return response.body();
    }

    public Pokemon getPokemon(String name) throws PokeAPIException {
        Gson gson = new Gson();
        Pokemon out;
        String json = call("pokemon/" + name.toLowerCase());
        try {
            out = gson.fromJson(json, Pokemon.class);
        }catch (Exception e) {
            throw new PokeAPIException("PokeAPI", e.getMessage());
        }

        Pattern pattern = Pattern.compile("\"base_stat\":(\\d+),\"effort\":\\d+,\"stat\":\\{\"name\":\"hp\"");
        Matcher matcher = pattern.matcher(json);

        if(!matcher.find()) {
            throw new PokeAPIException("PokeAPI", "Valore HP non trovati.");
        }

        out.hp = Integer.parseInt(matcher.group(1));

        return out;
    }

}
