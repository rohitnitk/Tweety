package com.twitter.api.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpService {

    private final static String Authorization = "Authorization";

    HttpClient client = HttpClient.newHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();

    public <T> HttpRequest post(String url, String authorization, T body) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header(Authorization, authorization)
                .POST(BodyPublishers.ofString(convertToJsonString(body)))
                .build();
        return request;
    }

    public HttpResponse<String> getResponse(HttpRequest request) throws IOException, InterruptedException {
        HttpResponse<String> response = (HttpResponse<String>) client.send(request, BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
        return response;
    }

    private <T> String convertToJsonString(T body) throws JsonProcessingException {
        return objectMapper.writeValueAsString(body);
    }

}
