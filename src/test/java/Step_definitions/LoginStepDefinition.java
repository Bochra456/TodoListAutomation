package Step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import Pages.LoginPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


	public class LoginStepDefinition {

	    private Pages.LoginPage loginPage;

	    // ---- Background ----
	    @Given("je me connecte sur la page de connexion.")
	    public void jeMeConnecteSurLaPageDeConnexion() {
	       
	        loginPage = new LoginPage();
	        loginPage.ouvrirPageDeConnexion();
	    }

	    // ---- @login_vide ----
	    @Then("le bouton {string} est désactivé")
	    public void leBoutonEstDesactive(String nomBouton) {
	        assertTrue(
	                "Le bouton \"" + nomBouton + "\" devrait être désactivé quand les champs sont vides",
	                loginPage.isBoutonSoumettreDesactive()
	        );
	    }

	    // ---- @login_valide ----
	    @When("je saisis l email est valide {string} et le mot de passe valide {string}")
	    public void jeSaisisEmailValideEtMotDePasseValide(String email, String motDePasse) {
	        loginPage.saisirEmail(email);
	        loginPage.saisirMotDePasse(motDePasse);
	    }

	    // ---- @login_invalide ----
	    @When("je saisis l email est invalide {string} et le mot de passe invalide {string}")
	    public void jeSaisisEmailInvalideEtMotDePasseInvalide(String email, String motDePasse) {
	        loginPage.saisirEmail(email);
	        loginPage.saisirMotDePasse(motDePasse);
	    }

	    // ---- Commun aux deux scénarios (valide / invalide) ----
	    @When("je clique sur le bouton btn {string}")
	    public void jeCliqueSurLeBoutonBtn(String nomBouton) {
	        loginPage.cliquerSurBoutonSoumettre();
	    }

	    @Then("je suis connecté avec succès")
	    public void jeSuisConnecteAvecSucces() {
	        assertTrue("L'utilisateur devrait être redirigé vers ses tâches", loginPage.isConnexionReussie());
	    }

	    @Then("un message d'erreur {string} s affiche en rouge")
	    public void unMessageErreurSaffiche(String messageAttendu) {
	        assertEquals(messageAttendu, loginPage.getMessageErreur());
	        assertTrue(
	                "Le message d'erreur devrait s'afficher en rouge (obtenu: "
	                        + loginPage.getCouleurMessageErreur() + ")",
	                loginPage.isMessageErreurEnRouge()
	        );
	    }

	  
}
