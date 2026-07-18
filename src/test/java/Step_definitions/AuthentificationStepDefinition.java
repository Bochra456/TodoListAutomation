package Step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import Pages.AuthentificationPage;
import Pages.LoginPage;

public class AuthentificationStepDefinition {

    private AuthentificationPage authentificationPage;
    private LoginPage loginPage;

    // ---- Background ----
    @Given("je suis sur la page d’accueil de l’application TODOList")
    public void jeSuisSurLaPageDAccueil() {
        authentificationPage = new AuthentificationPage();
        loginPage = new LoginPage();
        authentificationPage.ouvrirPageAccueil();
    }

    // ---- @après_login ----
    @When("je suis connecté avec des identifiants valides")
    public void jeSuisConnecteAvecDesIdentifiantsValides() {
        loginPage.saisirEmail("test@test.com");
        loginPage.saisirMotDePasse("test");
        loginPage.cliquerSurBoutonSoumettre();
    }

    // ---- Commun aux deux scénarios ----
    @Then("le lien {string} est visible")
    public void leLienEstVisible(String nomLien) {
        assertTrue(
                "Le lien/bouton \"" + nomLien + "\" devrait être visible",
                authentificationPage.isElementVisible(nomLien));
    }

    @Then("le lien {string} est invisible")
    public void leLienEstInvisible(String nomLien) {
        assertFalse(
                "Le lien/bouton \"" + nomLien + "\" devrait être invisible",
                authentificationPage.isElementVisible(nomLien));
    }
}