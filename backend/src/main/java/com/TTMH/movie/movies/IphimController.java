package com.TTMH.movie.movies;

import com.TTMH.movie.tmdb.IphimClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/iphim")
public class IphimController {

    private final IphimClient iphim;

    public IphimController(IphimClient iphim) {
        this.iphim = iphim;
    }

    @GetMapping("/latest")
    public String latest(@RequestParam(defaultValue = "1") int page) {
        return iphim.latest(page);
    }

    @GetMapping("/list/{slug}")
    public String list(@PathVariable String slug,
                       @RequestParam(defaultValue = "1") int page) {
        return iphim.listByCategory(slug, page);
    }

    @GetMapping("/movie/{slug}")
    public String movie(@PathVariable String slug){
        return iphim.movieDetail(slug);
    }

    @GetMapping("/genre/{slug}")
    public String genre(@PathVariable String slug,
                        @RequestParam(defaultValue = "1") int page) {
        return iphim.listByGenre(slug, page);
    }

    @GetMapping("/country/{slug}")
    public String country(@PathVariable String slug,
                          @RequestParam(defaultValue = "1") int page) {
        return iphim.listByCountry(slug, page);
    }

    @GetMapping("/search")
    public String search(@RequestParam String keyword) {
        return iphim.search(keyword);
    }
}
