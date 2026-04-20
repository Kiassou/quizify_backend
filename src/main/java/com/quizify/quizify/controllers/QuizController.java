package com.quizify.quizify.controllers;

import com.quizify.quizify.entities.Quiz;
import com.quizify.quizify.repositories.QuizRepository; // Assure-toi d'avoir ce repo
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*") // Utile pour plus tard quand tu connecteras Flutter
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;

    // 1. VOIR TOUS LES QUIZ
    @GetMapping("/all")
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    // 2. VOIR UN QUIZ PAR ID
    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long id) {
        return quizRepository.findById(id)
                .map(quiz -> ResponseEntity.ok().body(quiz))
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. AJOUTER UN QUIZ 
    @PostMapping("/add")
    public Quiz addQuiz(@RequestBody Quiz quiz) {
        // Note : Si tu as une logique complexe de lien Question/Quiz, 
        // elle devrait idéalement être dans un QuizService.
        return quizRepository.save(quiz);
    }

    // 4. SUPPRIMER UN QUIZ
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id) {
        if (!quizRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        quizRepository.deleteById(id);
        return ResponseEntity.noContent().build(); 
    }
}