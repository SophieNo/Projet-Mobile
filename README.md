# 📱 Application Android – Mini-Jeux

## 📖 Description

Ce projet est une application mobile Android développée en Java, regroupant plusieurs mini-jeux éducatifs, interactifs et ludiques au sein d’une interface unique.

L’objectif est de proposer une expérience variée tout en mettant en pratique les concepts fondamentaux du développement Android :
- gestion des activités
- interaction utilisateur
- utilisation d’API
- persistance des données
- capteurs et géolocalisation

---

## 🎮 Fonctionnalités principales

L’application contient plusieurs mini-jeux indépendants :

### 🍄 Jeu des Champignons
- Jeu de réflexe
- Identifier les bons champignons
- Gestion du score et effets visuels

### 🌍 Jeu des Distances
- Basé sur la géolocalisation
- Deviner la distance entre le joueur et une ville
- Utilisation de la formule de Haversine

### 🎨 Jeu du Dessin
- Dessin libre sur écran tactile
- Choix de la couleur
- Rendu en temps réel avec Canvas

### 🌡️ Jeu des Températures
- Comparaison de températures entre villes
- Utilisation de l’API OpenWeatherMap
- Géolocalisation GPS

### 📸 Jeu des Photos
- Capture via la caméra
- Application de filtres (RGB, inversion, flou)
- Sauvegarde dans la galerie

### 🗳️ Midterm Race
- Jeu tactile rapide
- Avancer des jetons selon les zones de l’écran

### 💻 Quiz Informatique
- Questions récupérées depuis une API MySQL
- QCM avec score dynamique

### ⚙️ Circuit à bille
- Utilisation de l’accéléromètre
- Création de circuits personnalisés
- Détection de collisions

---

## 🏗️ Architecture

- Application modulaire (1 activité par jeu)
- Navigation via Intent explicites
- Page d’accueil centralisée
- Pages de description pour chaque jeu

---

## 🧰 Technologies utilisées

### 📱 Développement
- Java
- Android Studio

### 🔧 Fonctionnalités Android
- Activities & Intent
- Canvas & Views personnalisées
- SensorManager (accéléromètre)
- Camera API

### 🌐 API & Réseau
- OpenWeatherMap
- API PHP + MySQL
- HttpURLConnection / JSON

### 💾 Données
- SQLite
- SharedPreferences

### 📍 Géolocalisation
- FusedLocationProviderClient
- Google Maps (MapView)

---

## 🌍 Multilingue

- Support du français et de l’anglais
- Gestion via `strings.xml`
- Sauvegarde de la langue avec SharedPreferences

---

## 💾 Persistance des données

- Scores sauvegardés localement
- Stockage via SharedPreferences
- Données via SQLite

---

## 🔄 Gestion des performances

- Utilisation de traitements asynchrones pour :
  - appels API
  - base de données
  - géolocalisation

---

## 🧭 Navigation & UX

- Interface simple et intuitive
- Navigation fluide entre les jeux
- Feedback utilisateur (Toasts, AlertDialog)

---

## 🚀 Lancer le projet

1. Cloner le dépôt
2. Ouvrir avec Android Studio
3. Configurer un émulateur ou un appareil
4. Lancer l’application

---

## 📈 Améliorations possibles

- Ajout de nouveaux mini-jeux
- Mode multijoueur
- Sauvegarde cloud
- Classements en ligne
- Amélioration UI/UX

