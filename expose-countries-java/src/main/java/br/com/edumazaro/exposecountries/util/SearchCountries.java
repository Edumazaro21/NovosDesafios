package br.com.edumazaro.exposecountries.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SearchCountries {

    public static List<JSONObject> searchCountryesByRegionAndKeyword(String reqion, String keyword) throws Exception {
        String baseUrl = "http://localhost:8080/regions/search";
        List<JSONObject> paises = new ArrayList();

        int currentPage = 0;
        int totalPages = 1;

        HttpClient client = HttpClient.newHttpClient();

        while (currentPage <= totalPages) {
            try {
                String encodedRegion = URLEncoder.encode(reqion, StandardCharsets.UTF_8);
                String encodedKeyword = URLEncoder.encode(keyword, StandardCharsets.UTF_8);

                String url = String.format("%s?region=%s&keyWord=%s&page=%d", baseUrl, encodedRegion, encodedKeyword, currentPage);
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() == 200) {

                    JSONObject json = new JSONObject(response.body());
                    totalPages = json.getInt("totalPages");

                    JSONArray content = json.getJSONArray("data");

                    for (int i = 0; i < content.length(); i++) {
                        paises.add(content.getJSONObject(i));
                    }
                } else {
                    throw new IOException("Erro ao efetuar get. StatusCode: " + response.statusCode() + " - Body: " + response.body());
                }

                currentPage++;
            } catch (Exception e) {
                throw new Exception("Erro ao buscar paises", e);
            }
        }

        return paises;
    }

    public static void main(String[] args) throws Exception {
        List<JSONObject> paises = searchCountryesByRegionAndKeyword("América do Sul", "1");
        for (JSONObject pais : paises) {
            System.out.println(pais);
        }
    }
}
