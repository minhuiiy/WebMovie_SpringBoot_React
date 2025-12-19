package com.TTMH.movie.repo;

import com.TTMH.movie.domain.Favorite;
import com.TTMH.movie.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepo extends JpaRepository<Favorite, Long> {
    Optional<Favorite> findByUserAndMovieId(User user, Long movieId);
    List<Favorite> findAllByUserOrderByCreatedAtDesc(User user);
    void deleteByUserAndMovieId(User user, Long movieId);
}
