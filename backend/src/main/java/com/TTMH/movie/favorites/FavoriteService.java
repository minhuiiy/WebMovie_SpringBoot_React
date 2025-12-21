package com.TTMH.movie.favorites;

import com.TTMH.movie.common.BadRequestException;
import com.TTMH.movie.domain.Favorite;
import com.TTMH.movie.repo.FavoriteRepo;
import com.TTMH.movie.repo.UserRepo;
import com.TTMH.movie.tmdb.IphimClient;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class FavoriteService {

    private final FavoriteRepo favoriteRepo;
    private final UserRepo userRepo;
    private IphimClient client;

    public FavoriteService(FavoriteRepo favoriteRepo, UserRepo userRepo, IphimClient client) {
        this.favoriteRepo = favoriteRepo;
        this.userRepo = userRepo;
        this.client = client;
    }

    private String currentEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public void add(Long movieId) {
        var user = userRepo.findByEmail(currentEmail()).orElseThrow();
        if (favoriteRepo.findByUserAndMovieId(user, movieId).isPresent()) return;

        favoriteRepo.save(Favorite.builder()
                .user(user)
                .movieId(movieId)
                .createdAt(Instant.now())
                .build());
    }

    public void remove(Long movieId) {
        var user = userRepo.findByEmail(currentEmail()).orElseThrow();
        favoriteRepo.deleteByUserAndMovieId(user, movieId);
    }

    public List<Long> listMovieIds() {
        var user = userRepo.findByEmail(currentEmail()).orElseThrow();
        return favoriteRepo.findAllByUserOrderByCreatedAtDesc(user)
                .stream().map(Favorite::getMovieId).toList();
    }

    public List<String> listMovieDetails() {
        return listMovieIds().stream()
                .map(id -> {
                    try { return client.movieDetail(String.valueOf(id)); }
                    catch (Exception e) { throw new BadRequestException("Iphim lỗi với ID=" + id); }
                })
                .toList();
    }
}
