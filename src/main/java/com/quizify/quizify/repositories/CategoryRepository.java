package com.quizify.quizify.repositories;

import com.quizify.quizify.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
