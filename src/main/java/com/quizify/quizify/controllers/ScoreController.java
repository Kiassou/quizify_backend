package com.quizify.quizify.controllers;

import com.quizify.quizify.entities.Quiz;
import com.quizify.quizify.entities.Score;
import com.quizify.quizify.entities.User; // Ajout de l'import manquant
import com.quizify.quizify.repositories.ChoiceRepository;
import com.quizify.quizify.repositories.QuizRepository;
import com.quizify.quizify.repositories.ScoreRepository;
import com.quizify.quizify.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/scores") // Ajout de /api pour la cohérence avec tes autres controllers
@CrossOrigin("*")
public class ScoreController {

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChoiceRepository choiceRepository;

    // 1. VOIR TOUS LES SCORES
    @GetMapping("/all")
    public List<Score> getAllScores() {
        return scoreRepository.findAll();
    }

    // 2. VOIR UN SCORE PAR ID
    @GetMapping("/{id}")
    public ResponseEntity<Score> getScoreById(@PathVariable Long id) {
        return scoreRepository.findById(id)
                .map(score -> ResponseEntity.ok().body(score))
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. VOIR L'HISTORIQUE COMPLET D'UN UTILISATEUR
    @GetMapping("/user/{userId}/history")
    public ResponseEntity<List<Score>> getUserHistory(@PathVariable Long userId) {
        List<Score> history = scoreRepository.findByUserId(userId);
        if (history.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(history);
    }

    // 4. SUPPRIMER UN SCORE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScore(@PathVariable Long id) {
        if (!scoreRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        scoreRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // 5. CALCULER ET ENREGISTRER LE SCORE AUTOMATIQUEMENT
    @PostMapping("/calculate/{quizId}/{userId}")
    public ResponseEntity<Score> calculateAndSaveScore(
            @PathVariable Long quizId,
            @PathVariable Long userId,
            @RequestBody List<Long> selectedChoiceIds) {

        Quiz quiz = quizRepository.findById(quizId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();

        int totalQuestions = quiz.getQuestions().size();
        int correctAnswers = 0;

        for (Long choiceId : selectedChoiceIds) {
            if (choiceRepository.findById(choiceId).map(c -> c.isCorrect()).orElse(false)) {
                correctAnswers++;
            }
        }

        int finalScoreValue = (totalQuestions == 0) ? 0 : (int) ((double) correctAnswers / totalQuestions * 100);

        Score score = new Score();
        score.setScoreValue(finalScoreValue);
        score.setUser(user);
        score.setQuiz(quiz);
        score.setDateAttempt(LocalDateTime.now());

        return ResponseEntity.ok(scoreRepository.save(score));
    }
}