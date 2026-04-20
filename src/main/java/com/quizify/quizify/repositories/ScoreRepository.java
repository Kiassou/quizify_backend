package com.quizify.quizify.repositories;

import com.quizify.quizify.entities.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findByUserId(Long userId);
    List<Score> findByQuizId(Long quizId);
}