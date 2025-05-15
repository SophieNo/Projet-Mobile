-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : jeu. 15 mai 2025 à 17:44
-- Version du serveur : 8.2.0
-- Version de PHP : 8.2.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `mobile`
--

-- --------------------------------------------------------

--
-- Structure de la table `quiz`
--

DROP TABLE IF EXISTS `quiz`;
CREATE TABLE IF NOT EXISTS `quiz` (
  `id` int NOT NULL AUTO_INCREMENT,
  `question` text COLLATE utf32_bin NOT NULL,
  `reponse_a` varchar(255) COLLATE utf32_bin NOT NULL,
  `reponse_b` varchar(255) COLLATE utf32_bin NOT NULL,
  `reponse_c` varchar(255) COLLATE utf32_bin NOT NULL,
  `reponse_d` varchar(255) COLLATE utf32_bin NOT NULL,
  `bonne_reponse` char(1) COLLATE utf32_bin NOT NULL,
  PRIMARY KEY (`id`)
) ;

--
-- Déchargement des données de la table `quiz`
--

INSERT INTO `quiz` (`id`, `question`, `reponse_a`, `reponse_b`, `reponse_c`, `reponse_d`, `bonne_reponse`) VALUES
(1, 'Que signifie HTML ?', 'HyperText Markup Language', 'HyperText Machine Language', 'Home Tool Markup Language', 'Hyperlinks and Text Markup Language', 'A'),
(2, 'Quel composant est le cerveau de l’ordinateur ?', 'RAM', 'CPU', 'Disque dur', 'Carte mère', 'B'),
(3, 'Quel est le rôle du système d’exploitation ?', 'Afficher des sites web', 'Contrôler les périphériques', 'Compiler du code', 'Exécuter des scripts JavaScript', 'B'),
(4, 'Quelle entreprise développe le système Android ?', 'Apple', 'Google', 'Microsoft', 'Samsung', 'B'),
(5, 'Qu’est-ce qu’un algorithme ?', 'Un type de logiciel', 'Un langage de programmation', 'Une suite d’instructions logiques', 'Un périphérique', 'C'),
(6, 'Que signifie CPU ?', 'Central Process Unit', 'Central Processing Unit', 'Computer Personal Unit', 'Central Processor Unit', 'B'),
(7, 'Quel est le rôle de la RAM ?', 'Stocker les fichiers à long terme', 'Traiter les images', 'Stocker temporairement les données en cours', 'Transmettre des données au processeur', 'C'),
(8, 'Lequel de ces langages est orienté objet ?', 'HTML', 'CSS', 'Java', 'SQL', 'C'),
(9, 'À quoi sert une base de données ?', 'Dessiner des interfaces', 'Gérer des fichiers systèmes', 'Stocker et manipuler des données', 'Créer des images', 'C'),
(10, 'Que signifie HTTP ?', 'Hyper Transfer Protocol', 'HyperText Transfer Protocol', 'HighText Transfer Protocol', 'Hyperlink Text Protocol', 'B'),
(11, 'Quel langage est principalement utilisé côté client ?', 'PHP', 'Python', 'JavaScript', 'Ruby', 'C'),
(12, 'Quelle extension correspond à un fichier exécutable sous Windows ?', '.txt', '.docx', '.exe', '.html', 'C'),
(13, 'Quel langage est utilisé pour styliser une page web ?', 'HTML', 'CSS', 'Python', 'Java', 'B'),
(14, 'Que signifie SSD ?', 'Solid State Drive', 'Secure Software Device', 'Simple System Drive', 'Serial Software Disk', 'A'),
(15, 'Quelle commande Git permet de sauvegarder les modifications ?', 'git push', 'git pull', 'git commit', 'git merge', 'C'),
(16, 'Qu’est-ce que Linux ?', 'Un logiciel antivirus', 'Un système d’exploitation', 'Un navigateur web', 'Une carte graphique', 'B'),
(17, 'Quel logiciel est un tableur ?', 'Word', 'Excel', 'Photoshop', 'Outlook', 'B'),
(18, 'Que signifie URL ?', 'Uniform Resource Locator', 'Universal Remote Link', 'Unified Resource Location', 'Unspecified Relative Link', 'A'),
(19, 'Qu’est-ce qu’une IP ?', 'Une image portable', 'Une instruction programmée', 'Un identifiant de protocole', 'Un identifiant d’un appareil sur un réseau', 'D'),
(20, 'Quelle balise HTML insère une image ?', '<text>', '<src>', '<img>', '<pic>', 'C'),
(21, 'Quel système de gestion de base de données est relationnel ?', 'MongoDB', 'PostgreSQL', 'Redis', 'Elasticsearch', 'B'),
(22, 'Quel langage est principalement utilisé pour les scripts serveur ?', 'CSS', 'PHP', 'HTML', 'JSON', 'B'),
(23, 'Que signifie BIOS ?', 'Basic Input Output System', 'Binary Integrated Operating System', 'Basic Internal Output Software', 'Basic In/Out Setup', 'A'),
(24, 'Quelle technologie permet de virtualiser des machines ?', 'Bluetooth', 'Docker', 'SMTP', 'HTML', 'B'),
(25, 'Quel langage est compilé ?', 'Java', 'Python', 'PHP', 'HTML', 'A'),
(26, 'Quel est le format standard des fichiers JSON ?', 'Tableau', 'XML', 'Texte structuré', 'Image', 'C'),
(27, 'Comment nomme-t-on un virus qui chiffre les fichiers pour une rançon ?', 'Ver', 'Ransomware', 'Cheval de Troie', 'Rootkit', 'B'),
(28, 'Quel protocole est sécurisé ?', 'HTTP', 'FTP', 'SSH', 'TELNET', 'C'),
(29, 'Quel composant stocke les données à long terme ?', 'RAM', 'Cache', 'SSD/HDD', 'GPU', 'C'),
(30, 'Quel est l’objectif principal d’un pare-feu ?', 'Optimiser le réseau', 'Bloquer les virus', 'Contrôler les flux réseau', 'Augmenter la vitesse du CPU', 'C'),
(31, 'Que fait un compilateur ?', 'Exécute un programme', 'Transforme le code source en binaire', 'Gère la mémoire', 'Affiche les pages web', 'B'),
(32, 'Que signifie DNS ?', 'Data Network System', 'Domain Name System', 'Digital Network Server', 'Domain Numeric Server', 'B'),
(33, 'Que permet une API ?', 'Programmer une interface graphique', 'Développer une IA', 'Communiquer entre logiciels', 'Gérer des utilisateurs', 'C'),
(34, 'Quel outil permet le contrôle de version ?', 'Eclipse', 'Sublime Text', 'Git', 'VLC', 'C'),
(35, 'Qu’est-ce que le cloud computing ?', 'Stockage local', 'Programmation en local', 'Calculs graphiques', 'Services distants accessibles par Internet', 'D'),
(36, 'Quelle est la fonction de la carte graphique ?', 'Afficher des images', 'Calculer le son', 'Stocker des documents', 'Envoyer des e-mails', 'A'),
(37, 'Quel protocole envoie des mails ?', 'POP3', 'SMTP', 'HTTP', 'IMAP', 'B'),
(38, 'Que signifie CSS ?', 'Cascading Style Sheets', 'Creative Style Sheet', 'Computer Style Syntax', 'Central Style Sheet', 'A'),
(39, 'Quel outil permet de gérer des conteneurs ?', 'Docker', 'Kubernetes', 'GitHub', 'Bitbucket', 'A'),
(40, 'Qu’est-ce qu’un cookie en informatique ?', 'Un fichier de mise en cache', 'Un type de malware', 'Une information stockée par un site sur l’utilisateur', 'Une archive compressée', 'C'),
(41, 'Quelle commande Linux affiche le contenu d’un dossier ?', 'cd', 'mkdir', 'ls', 'rm', 'C'),
(42, 'Que signifie LAN ?', 'Large Area Network', 'Long Access Network', 'Local Area Network', 'Local Access Node', 'C'),
(43, 'Quel outil est utilisé pour le développement mobile Android ?', 'Xcode', 'Android Studio', 'Visual Studio Code', 'NetBeans', 'B'),
(44, 'Qu’est-ce que SQL ?', 'Un protocole de réseau', 'Un langage de requête de base de données', 'Un langage de programmation web', 'Un système de fichiers', 'B'),
(45, 'Que permet le langage Python ?', 'Créer des pages web uniquement', 'Définir des styles CSS', 'Automatiser et programmer divers scripts', 'Lire des vidéos', 'C'),
(46, 'Qu’est-ce qu’un script ?', 'Un périphérique d’entrée', 'Un type de malware', 'Un programme écrit pour automatiser des tâches', 'Une clé de sécurité', 'C'),
(47, 'Quel format est utilisé pour les images ?', '.doc', '.png', '.html', '.css', 'B'),
(48, 'Quelle touche permet souvent d’actualiser une page web ?', 'F5', 'F1', 'F12', 'F10', 'A'),
(49, 'Quel langage est utilisé pour créer des applications iOS ?', 'Python', 'Java', 'Swift', 'Kotlin', 'C'),
(50, 'À quoi sert un routeur ?', 'À afficher des images', 'À connecter des réseaux entre eux', 'À augmenter la RAM', 'À compiler du code', 'B');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
