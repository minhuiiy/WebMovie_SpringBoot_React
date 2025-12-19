package com.TTMH.movie.repo;

import com.TTMH.movie.domain.User;
import com.TTMH.movie.domain.WatchHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WatchHistoryRepo extends JpaRepository<WatchHistory, Long> {
    List<WatchHistory> findAllByUserOrderByWatchedAtDesc(User user);
}
