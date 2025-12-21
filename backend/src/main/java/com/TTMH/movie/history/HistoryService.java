package com.TTMH.movie.history;

import com.TTMH.movie.domain.WatchHistory;
import com.TTMH.movie.history.dto.SaveProgressRequest;
import com.TTMH.movie.repo.UserRepo;
import com.TTMH.movie.repo.WatchHistoryRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Service
public class HistoryService {

    private final WatchHistoryRepo repo;
    private final UserRepo userRepo;

    public HistoryService(WatchHistoryRepo repo, UserRepo userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    private String currentEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public void saveProgress (Long movieId, SaveProgressRequest req) {
        var user = userRepo.findByEmail(currentEmail()).orElseThrow();
        repo.save(WatchHistory.builder()
                .user(user)
                .movieId(movieId)
                .progressSeconds(req.getProgressSeconds())
                .watchedAt(Instant.now())
                .build());
    }

    public List<Map<String, Object>> list() {
        var user = userRepo.findByEmail(currentEmail()).orElseThrow();
        return repo.findAllByUserOrderByWatchedAtDesc(user).stream()
                .map(h -> Map.<String, Object>of(
                        "id", h.getId(),
                        "movieId", h.getMovieId(),
                        "progressSeconds", h.getProgressSeconds(),
                        "watchedAt", h.getWatchedAt()
                ))
                .toList();
    }
}
