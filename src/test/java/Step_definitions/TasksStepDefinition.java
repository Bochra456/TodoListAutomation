package Step_definitions;

	import io.cucumber.java.en.Given;
	import io.cucumber.java.en.Then;
	import io.cucumber.java.en.When;
	import Pages.LoginPage;
	import Pages.TasksPage;

	import static org.junit.Assert.assertEquals;
	import static org.junit.Assert.assertTrue;

	public class TasksStepDefinition {

	    private LoginPage loginPage;
	    private TasksPage tasksPage;

	    // Mémorise la tâche en cours de manipulation entre les steps d'un même scénario
	    private String nomTacheCourante;
	    private String descriptionTacheCourante;

	    // ---- Background ----
	    @Given("je suis connecté sur l’application TODOList")
	    public void jeSuisConnecteSurLApplication() {
	        loginPage = new LoginPage();
	        tasksPage = new TasksPage();

	        loginPage.ouvrirPageDeConnexion();
	        loginPage.saisirEmail("test@test.com");
	        loginPage.saisirMotDePasse("test");
	        loginPage.cliquerSurBoutonSoumettre();
	        // Après connexion, l'application redirige automatiquement vers "/tasks"
	    }

	    // ---- @Champs_vides ----
	    @When("je ne remplis pas le nom de tâche et la description")
	    public void jeNeRemplisPasLesChamps() {
	        // Rien à faire : les champs sont vides par défaut au chargement de la page
	    }

	    @Then("le bouton {string} est désactivée")
	    public void leBoutonEstDesactivee(String nomBouton) {
	        assertTrue(
	                "Le bouton \"" + nomBouton + "\" devrait être désactivé quand les champs sont vides",
	                tasksPage.isBoutonAjouterDesactive()
	        );
	    }

	    @Then("le localStorage est vide")
	    public void leLocalStorageEstVide() {
	        assertTrue("Le localStorage devrait être vide", tasksPage.isLocalStorageVide());
	    }

	    // ---- @ajouter ----
	    @When("je saisis le nom de tâche {string} et la description {string}")
	    public void jeSaisisNomEtDescription(String nom, String description) {
	        nomTacheCourante = nom;
	        descriptionTacheCourante = description;
	        tasksPage.saisirNomTache(nom);
	        tasksPage.saisirDescriptionTache(description);
	    }

	    @When("je clique sur le bouton {string}")
	    public void jeCliqueSurLeBouton(String nomBouton) {
	        tasksPage.cliquerSurBoutonAjouter();
	    }

	    @Then("la tâche {string}est affichée")
	    public void laTacheEstAffichee(String tacheAttendue) {
	        String[] parties = tacheAttendue.split(" ", 2);
	        String nom = parties[0];
	        String description = parties.length > 1 ? parties[1] : "";

	        assertTrue(
	                "La tâche \"" + tacheAttendue + "\" devrait être affichée",
	                tasksPage.isTacheAffichee(nom, description)
	        );
	    }

	    @Then("le localStorage contient la nouvelle tâche")
	    public void leLocalStorageContientLaNouvelleTache() {
	        assertTrue(
	                "Le localStorage devrait contenir la tâche ajoutée",
	                tasksPage.localStorageContientTache(nomTacheCourante, descriptionTacheCourante));
	    }

	    // ---- Given commun à @Modifier et @supprimer ----
	    @Given("une tâche {string} existe")
	    public void uneTacheExiste(String tacheComplete) {
	        String[] parties = tacheComplete.split(" ", 2);
	        nomTacheCourante = parties[0];
	        descriptionTacheCourante = parties.length > 1 ? parties[1] : "";

	        tasksPage.ajouterTacheDirectementViaLocalStorage(nomTacheCourante, descriptionTacheCourante);
	    }

	    // ---- @Modifier ----
	    @Then("je marque la tâche comme terminée")
	    public void jeMarqueLaTacheCommeTerminee() {
	        tasksPage.marquerTacheCommeTerminee(nomTacheCourante);
	    }

	    @Then("le status de la tâche est {string}")
	    public void leStatusDeLaTacheEst(String statusAttendu) {
	        // L'appli affiche "Complétée" / "Non complétée" plutôt que
	        // "terminée" / "non terminée" utilisés dans la feature : on adapte.
	        String statusReel = tasksPage.getStatusTache(nomTacheCourante);
	        String statusNormalise = statusReel.equals("Complétée") ? "terminée" : "non terminée";
	        assertEquals(statusAttendu, statusNormalise);
	    }

	    @Then("le localStorage est mis à jour")
	    public void leLocalStorageEstMisAJour() {
	        assertTrue(
	                "Le localStorage devrait refléter la tâche marquée comme terminée",
	                tasksPage.localStorageContientTacheTerminee(nomTacheCourante, descriptionTacheCourante));
	    }

	    // ---- @supprimer ----
	    @Then("je supprime la tâche")
	    public void jeSupprimeLaTache() {
	        tasksPage.supprimerTache(nomTacheCourante);
	    }

	    @Then("la tâche est supprimée le localStorage ne contient plus cette tâche")
	    public void laTacheEstSupprimeeEtLeLocalStorageNeContientPlusCetteTache() {
	        assertTrue("La tâche ne devrait plus être affichée", tasksPage.isTacheSupprimee(nomTacheCourante));
	        assertTrue(
	                "Le localStorage ne devrait plus contenir cette tâche",
	                tasksPage.localStorageNeContientPlusTache(nomTacheCourante));
	    }

	    
	}
	

