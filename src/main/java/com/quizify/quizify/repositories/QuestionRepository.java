package com.quizify.quizify.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizify.quizify.entities.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {}