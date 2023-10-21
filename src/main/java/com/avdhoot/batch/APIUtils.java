package com.avdhoot.batch;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class APIUtils {

    private static String URL = "https://jsonplaceholder.typicode.com/comments";
    private static Logger logger = LoggerFactory.getLogger(APIUtils.class);

    public static Deque<Comment> fetchData() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI(URL))
                    .build();
        } catch (URISyntaxException e) {
            logger.error(e.getLocalizedMessage());
        }

        HttpResponse response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            logger.error(e.getLocalizedMessage());
        }
        JSONArray jsonArray = new JSONArray(response.body().toString());
        Deque<Comment> data = new ArrayDeque<>();

        jsonArray.forEach(it -> data.add(
                new Gson().fromJson(it.toString(), Comment.class)
        ));
        logger.debug("Data size : "+ data.size());
        System.out.println("###########################");
        System.out.println("ISME AAGAYA MEIN");
        System.out.println("###########################");
        return data;
    }
}
