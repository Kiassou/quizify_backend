package com.quizify.quizify.controllers;

import com.quizify.quizify.entities.User;
import com.quizify.quizify.repositories.UserRepository;
import com.quizify.quizify.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "https://kiassou.github.io")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    private Map<String, String> verificationCodes = new HashMap<>();

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
    String username = credentials.get("username");
    Optional<User> userOpt = userRepository.findByUsername(username);

    if (userOpt.isPresent()) {
        User user = userOpt.get();
        Map<String, Object> response = new HashMap<>();
        response.put("id", user.getId());
        response.put("username", user.getUsername());
        response.put("role", user.getRole());
        response.put("prenom", user.getPrenom());
        response.put("nom", user.getNom());
        return ResponseEntity.ok(response);
    } else {
        // Crée une map pour l'erreur au lieu d'une simple String
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Identifiant inconnu.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }
 }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepository.existsByUsername((String) user.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ce nom d'utilisateur est déjà pris.");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cet email est déjà utilisé.");
        }

        User savedUser = userRepository.save(user);
        try {
            emailService.sendWelcomeEmail(savedUser.getEmail(), savedUser.getUsername());
        } catch (Exception e) {
            System.err.println("Erreur email : " + e.getMessage());
        }
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/send-code")
    public ResponseEntity<?> sendCode(@RequestParam String email) {
        return userRepository.findByEmail(email).map(user -> {
            String code = String.format("%06d", new Random().nextInt(999999));
            verificationCodes.put(email, code);
            emailService.sendVerificationCode(email, code);
            return ResponseEntity.ok("Code envoyé !");
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email inconnu."));
    }

    @PostMapping("/verify-code")
    public ResponseEntity<?> verifyCode(@RequestParam String email, @RequestParam String code) {
        if (verificationCodes.containsKey(email) && verificationCodes.get(email).equals(code)) {
            return userRepository.findByEmail(email)
                .map(user -> {
                    verificationCodes.remove(email);
                    // Même ici, on renvoie une Map pour être safe
                    Map<String, Object> response = new HashMap<>();
                    response.put("username", user.getUsername());
                    return ResponseEntity.ok(response);
                }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Code incorrect.");
    }
}