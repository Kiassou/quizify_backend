package com.quizify.quizify.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizify.quizify.entities.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {}