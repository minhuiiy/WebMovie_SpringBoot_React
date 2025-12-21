package com.TTMH.movie.comments;

import com.TTMH.movie.comments.dto.CreateCommentRequest;
import com.TTMH.movie.domain.Comment;
import com.TTMH.movie.repo.CommentRepo;
import com.TTMH.movie.repo.UserRepo;
import io.jsonwebtoken.lang.Objects;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Service
public class CommentService {

    private final CommentRepo commentRepo;
    private final UserRepo userRepo;

    public CommentService(CommentRepo commentRepo, UserRepo userRepo) {
        this.commentRepo = commentRepo;
        this.userRepo = userRepo;
    }

    private String currentEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public void add(Long movieId, CreateCommentRequest req) {
        var user = userRepo.findByEmail(currentEmail()).orElseThrow();
        commentRepo.save(Comment.builder()
                .user(user)
                .movieId(movieId)
                .content(req.getContent())
                .createAt(Instant.now())
                .build());
    }

    public List<Map<String, Object>> list(Long movieId) {
        return commentRepo.findAllByMovieIdOrderByCreatedAtDesc(movieId).stream()
                .map(c -> Map.<String, Object>of(
                        "id", c.getId(),
                        "movieId", c.getMovieId(),
                        "content", c.getContent(),
                        "createdAt", c.getCreateAt(),
                        "userEmail", c.getUser().getEmail(),
                        "fullName", c.getUser().getFullName()
                ))
                .toList();
    }

}
