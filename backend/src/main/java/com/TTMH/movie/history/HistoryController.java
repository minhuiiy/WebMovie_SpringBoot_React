package com.TTMH.movie.history;

import com.TTMH.movie.history.dto.SaveProgressRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/history")
public class HistoryController {

    private final HistoryService service;

    public HistoryController(HistoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<Map<String, Object>> list() {
        return service.list();
    }

    @PostMapping("/{movieId}")
    public void save(@PathVariable Long movieId, @Valid @RequestBody SaveProgressRequest req) {
        service.saveProgress(movieId, req);
    }
}
