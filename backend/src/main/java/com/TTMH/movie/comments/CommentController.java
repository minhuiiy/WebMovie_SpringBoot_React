package com.TTMH.movie.comments;

import com.TTMH.movie.comments.dto.CreateCommentRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Map<String, Object>> list(@RequestParam Long movieId) {
        return service.list(movieId);
    }

    @PostMapping
    public void add(@RequestParam Long movieId, @Valid @RequestBody CreateCommentRequest req) {
        service.add(movieId, req);
    }
}
