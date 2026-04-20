package com.quizify.quizify.repositories;

import com.quizify.quizify.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    // Récupérer les notifications de la plus récente à la plus ancienne
    List<Notification> findAllByOrderByCreatedAtDesc();
}
