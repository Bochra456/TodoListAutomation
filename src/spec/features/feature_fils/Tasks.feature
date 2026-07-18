@tasks
Feature: Gestion des tâches sur l’ application TodoList

  En tant qu’utilisateur authentifié,
  Je souhaite ajouter, modifier et supprimer des tâches
  Afin de gérer ma liste de tâches et de vérifier que les données sont correctement enregistrées dans le localStorage. 
 
 Background:
 
Given je suis connecté sur l’application TODOList


@Champs_vides
Scenario: laisser les champs vides
When je ne remplis pas le nom de tâche et la description
Then le localStorage est vide
And le bouton "Ajouter la tâche" est désactivée 


@ajouter
Scenario: Ajouter une tâche 
When je saisis le nom de tâche "Test" et la description "education"
And je clique sur le bouton "Ajouter la tâche"
Then la tâche "Test education"est affichée
And le localStorage contient la nouvelle tâche
 
@Modifier
Scenario: Modifier un status de tâche
Given une tâche "Test education" existe
Then je marque la tâche comme terminée
And le status de la tâche est "terminée"
And le localStorage est mis à jour


@supprimer
Scenario: Supprimer une tâche
Given une tâche "Test education" existe
Then je supprime la tâche 
And la tâche est supprimée le localStorage ne contient plus cette tâche
