# 🎯 Quizify - Backend API

Quizify est une API REST robuste développée avec **Spring Boot**, conçue pour gérer une plateforme de quiz interactive. Ce backend gère les utilisateurs, la structure des quiz, et intègre un moteur de calcul de score intelligent.

## 🚀 Technologies utilisées
* **Java 17** & **Spring Boot 3**
* **MySQL** (Base de données relationnelle)
* **Spring Data JPA / Hibernate** (ORM)
* **Debian Linux** (Environnement de développement)
* **Maven** (Gestionnaire de dépendances)

## 🏗️ Architecture du Projet
Le projet suit une architecture en couches (Controller, Service, Repository, Entity) pour assurer une maintenance facile et une séparation des responsabilités.



### Entités principales :
1. **User** : Gestion des profils (Username et Email uniques).
2. **Category** : Organisation des quiz par thématiques.
3. **Quiz** : Structure contenant les questions et les choix.
4. **Score** : Suivi des performances et historique des utilisateurs.

## 🛠️ Endpoints principaux de l'API

### 👤 Utilisateurs (`/users`)
* `POST /register` : Inscription d'un nouvel utilisateur avec email de bienvenue.
* `POST /login` : Connexion simplifiée par nom d'utilisateur.
* `GET /all` : Liste tous les utilisateurs.
* `GET /{id}` : Détails d'un compte précis.
* `GET /{id}` : Détails d'un compte précis.
* `POST /forgot-username` : Récupération : Envoi de l'identifiant par email.
* `DELETE /{id}` :Suppression de compte,

### 📝 Quiz & Questions (`/quiz`)
* `GET /all` : Récupère la liste complète des quiz avec questions et choix.
* `POST /add` : Ajoute un nouveau quiz en base de données.

### 📊 Système de Scores (`/scores`)
* `POST /calculate/{quizId}/{userId}` : **Le moteur de calcul**. Envoie les réponses choisies, le serveur vérifie la validité et enregistre le score final automatiquement.
* `GET /user/{userId}/history` : Récupère tous les scores passés d'un utilisateur spécifique.
* `DELETE /{id}` : Supprime un enregistrement de score.

### 🔔 Système de Notifications (Broadcast)
* `POST /notifications/add` : Permet à l'administrateur de diffuser un message à tous les utilisateurs.
* `GET /notifications/all` : Permet aux utilisateurs de récupérer les dernières annonces (triées par date).
* `DELETE /notifications/{id}` : Suppression d'une notification.

### 📧 Service d'Emailing (MimeMessage & HTML)
* **Bienvenue Automatique** : Lors de chaque inscription (`/register`), un email de bienvenue stylisé est envoyé à l'utilisateur.
* **Format HTML/CSS** : Utilisation de templates MimeMessage pour un rendu professionnel avec logo et boutons d'action.
* **Asynchronisme de sécurité** : L'envoi est encapsulé dans un bloc `try-catch` pour garantir que l'inscription réussit même en cas d'indisponibilité du serveur SMTP.


### 🔍 Récupération de compte
* **Oubli d'identifiant** : Un endpoint dédié permet à l'utilisateur de renseigner son email pour recevoir instantanément son `username` par email.
* **Sécurité & Confidentialité** : Seul le propriétaire de l'adresse email enregistrée peut récupérer l'identifiant, garantissant une protection contre l'usurpation simple.
  

## ⚙️ Installation et Configuration

1. **Cloner le projet** :
   ```bash
   git clone [https://github.com/Kiassou/quizify_backend.git](https://github.com/Kiassou/quizify_backend.git)
2. **Configuration MySQL** :
   Créez une base de données nommée quizify et configurez vos accès dans le fichier src/main/resources/application.properties.

3. **Lancer l'application** :

  ```Bash
      ./mvnw spring-boot:run
```
## 📈 Fonctionnalités avancées
* Gestion des circularités JSON : Utilisation de @JsonIgnore pour des réponses API propres.

* Intégrité des données : Contraintes SQL (Foreign Keys) pour assurer la cohérence entre les quiz et les résultats.

* Calcul côté serveur : Sécurité renforcée en empêchant la manipulation des scores côté client.

  
### 👨‍💻 Développeur
**Kiassou** – *Étudiant en Développement Full-Stack*
