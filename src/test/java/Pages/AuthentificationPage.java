package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import utils.Setup;

/**
 * Page Object représentant la navigation de l'application
 * (composant src/components/Navbar.js) et le bouton "Soumettre"
 * du formulaire de login (src/components/Home.js).
 *
 * ATTENTION : dans le code actuel de Navbar.js, les liens "Home",
 * "Tâches" et "Déconnexion" sont toujours affichés, sans condition
 * liée à l'état de connexion (pas d'appel à auth.isLogged()).
 * Cette Page Object est écrite pour le comportement attendu par la
 * feature ; certaines assertions échoueront tant que cette logique
 * conditionnelle n'existe pas côté application.
 */
public class AuthentificationPage {

    private final By lienHome = By.linkText("Home");
    private final By lienTaches = By.linkText("Tâches");
    private final By lienDeconnexion = By.xpath("//a[normalize-space()='Déconnexion']");
    private final By boutonSoumettre = By.cssSelector("input[type='submit']");

    private final String urlAccueil = "http://localhost:3000/";

    public void ouvrirPageAccueil() {
        Setup.getDriver().get(urlAccueil);
    }

    /**
     * Indique si l'élément correspondant au nom donné ("Home", "Tâches",
     * "Déconnexion" ou "Soumettre") est visible à l'écran.
     * Retourne false si l'élément est absent du DOM (pas d'exception levée).
     */
    public boolean isElementVisible(String nom) {
        By locator = getLocatorPourNom(nom);
        try {
            return Setup.getDriver().findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private By getLocatorPourNom(String nom) {
        switch (nom) {
            case "Home":
                return lienHome;
            case "Tâches":
                return lienTaches;
            case "Déconnexion":
                return lienDeconnexion;
            case "Soumettre":
                return boutonSoumettre;
            default:
                throw new IllegalArgumentException("Lien/bouton inconnu : " + nom);
        }
    }
}