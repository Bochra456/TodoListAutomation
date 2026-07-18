@authentification
Feature: Affichage des liens de navigation avant et après l’authentification sur l’application TodoList

  En tant qu utilisateur,
  Je souhaite visualiser les liens disponibles avant et après mon authentification
  Afin de vérifier que la navigation est adaptée à mon état de connexion. 

Background:
Given je suis sur la page d’accueil de l’application TODOList

@avant_login
Scenario: navigation avant l’authentification
Then le lien "Home" est visible
And le lien "Soumettre" est visible
And le lien "Tâches" est invisible
And le lien "Déconnexion" est invisible


@après_login
Scenario: navigation après l’authentification
When je suis connecté avec des identifiants valides
Then le lien "Home" est visible
And le lien "Tâches" est visible
And le lien "Déconnexion" est visible
And le lien "Soumettre" est invisible
