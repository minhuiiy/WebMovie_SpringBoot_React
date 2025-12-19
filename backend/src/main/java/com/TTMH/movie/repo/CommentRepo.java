package com.TTMH.movie.repo;

import com.TTMH.movie.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface CommentRepo extends JpaRepository<Comment, Long> {
    List<Comment> findAllByMovieIdOrderByCreatedAtDesc(Long movieId);
}
