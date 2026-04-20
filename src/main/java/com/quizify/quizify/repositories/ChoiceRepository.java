package com.quizify.quizify.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quizify.quizify.entities.Choice;


@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Long> {
    // Cette interface permet d'utiliser findById pour vérifier si une réponse est correcte
}