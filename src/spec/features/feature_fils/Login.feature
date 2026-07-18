@login
Feature: Authentification sur l application TodoList

  En tant qu utilisateur,
  Je souhaite me connecter à l application TodoList
  Afin d’accéder à mes tâches.
  
  Background:
  Given je me connecte sur la page de connexion.

@login_vide
Scenario: Vérifier que le bouton soumettre est désactivé si les champs sont vides
Then le bouton "soumettre" est désactivé

@login_valide
Scenario: Se connecter avec des identifiants valides
When je saisis l email est valide "test@test.com" et le mot de passe valide "test"
And je clique sur le bouton btn "soumettre"
Then je suis connecté avec succès
@login_invalide
Scenario: Se connecter avec des identifiants invalides
When je saisis l email est invalide "test1@gmail" et le mot de passe invalide "test1"
And je clique sur le bouton btn "soumettre" 
Then un message d'erreur "Désolé, les identifiants sont incorrects." s affiche en rouge
