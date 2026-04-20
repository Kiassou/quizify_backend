package com.quizify.quizify.controllers;

import com.quizify.quizify.entities.Notification;
import com.quizify.quizify.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@CrossOrigin("*")
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;

    // 1. L'ADMIN AJOUTE UNE NOTIFICATION
    @PostMapping("/add")
    public Notification sendNotification(@RequestBody Notification notification) {
        return notificationRepository.save(notification);
    }

    // 2. TOUS LES UTILISATEURS RÉCUPÈRENT LES NOTIFICATIONS
    @GetMapping("/all")
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAllByOrderByCreatedAtDesc();
    }

    // 3. SUPPRIMER UNE NOTIFICATION ANCIENNE
    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable Long id) {
        notificationRepository.deleteById(id);
    }
}