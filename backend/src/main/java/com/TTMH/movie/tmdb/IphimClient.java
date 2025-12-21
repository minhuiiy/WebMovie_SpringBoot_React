package com.TTMH.movie.tmdb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import org.springframework.http.HttpHeaders;

@Component
public class IphimClient {
    
    private final RestClient client;
    
    public IphimClient(@Value("${iphim.base-url}") String baseUrl){
        this.client = RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.ACCEPT, "application/json")
                .build();
    }

    public String latest(int page) {
        return client.get()
                .uri(uri -> uri.path("api/films/phim-moi-cap-nhat")
                        .queryParam("page", page)
                        .build())
                .retrieve()
                .body(String.class);
    }

    public String listByCategory(String slug, int page) {
        return client.get()
                .uri(uri -> uri.path("/api/films/danh-sach/{slug}")
                        .queryParam("page", page)
                        .build(slug))
                .retrieve()
                .body(String.class);
    }

    public String movieDetail(String slug) {
        return client.get()
                .uri(uri -> uri.path("/api/phim/{slug}")
                    .build(slug))
                .retrieve()
                .body(String.class);
    }

    public String listByGenre(String slug, int page) {
        return client.get()
                .uri(uri -> uri.path("/api/films/the-loai/{slug}")
                        .queryParam("page", page)
                        .build())
                .retrieve()
                .body(String.class);
    }

    public String listByCountry(String slug, int page) {
        return client.get()
                .uri(uri -> uri.path("/api/films/quoc-gia/{slug}")
                        .queryParam("page", page)
                        .build())
                .retrieve()
                .body(String.class);
    }

    public String search(String keyword) {
        return client.get()
                .uri(uri -> uri.path("/api/films/search")
                        .queryParam("keyword", keyword)
                        .build())
                .retrieve()
                .body(String.class);
    }
}
