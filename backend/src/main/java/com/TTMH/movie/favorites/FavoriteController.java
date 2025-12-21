package com.TTMH.movie.favorites;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    private final FavoriteService service;

    public FavoriteController(FavoriteService service) {
        this.service = service;
    }

    @GetMapping
    public List<String> listDetails() {
        return service.listMovieDetails();
    }

    @PostMapping("/{movieId}")
    public void add(@PathVariable Long movieId) {
        service.add(movieId);
    }

    @DeleteMapping("/{movieId}")
    public void remove(@PathVariable Long movieId) {
        service.remove(movieId);
    }
}
