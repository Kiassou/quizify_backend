package com.quizify.quizify.services;

import com.quizify.quizify.entities.Choice;
import com.quizify.quizify.entities.Question;
import com.quizify.quizify.entities.Quiz;
import com.quizify.quizify.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    public List<Quiz> obtenirTousLesQuiz() {
        return quizRepository.findAll();
    }

    public Quiz addQuiz(Quiz quiz) {
    if (quiz.getQuestions() != null) {
        for (Question q : quiz.getQuestions()) {
            q.setQuiz(quiz); // C'est CA qui remplit la colonne quiz_id
            
            // Fais la même chose pour les choix si nécessaire
            if (q.getChoices() != null) {
                for (Choice c : q.getChoices()) {
                    c.setQuestion(q);
                }
            }
        }
    }
    return quizRepository.save(quiz);
}
}