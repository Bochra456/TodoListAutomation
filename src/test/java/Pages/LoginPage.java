package Pages;

import org.openqa.selenium.By;

import utils.Setup;


/**
 * Page Object représentant la page de connexion de l'application
 * react-todolist-qa (composant src/components/Home.js, monté sur "/").
 *
 * Remarque : les <input> de ce composant n'ont ni id ni name (les
 * attributs htmlFor="email"/"password" des <label> ne pointent vers
 * rien), les locators sont donc basés sur le type d'input.
 */
public class LoginPage {


    // ---- Locators basés sur le DOM réel de Home.js ----
    private final By emailInput = By.cssSelector("input[type='email']");
    private final By passwordInput = By.cssSelector("input[type='password']");
    private final By submitButton = By.cssSelector("input[type='submit'].btn-primary");
    private final By errorMessage = By.cssSelector(".alert.alert-danger");

    // La page de login est la route racine "/" de l'appli (Home.js)
    private final String loginUrl = "http://localhost:3000/";


    public void ouvrirPageDeConnexion() {
        Setup.getDriver().get(loginUrl);
    }

    public void saisirEmail(String email) {
        Setup.getDriver().findElement(emailInput).clear();
        Setup.getDriver().findElement(emailInput).sendKeys(email);
    }

    public void saisirMotDePasse(String motDePasse) {
        Setup.getDriver().findElement(passwordInput).clear();
        Setup.getDriver().findElement(passwordInput).sendKeys(motDePasse);
    }

    public void cliquerSurBoutonSoumettre() {
    	Setup.getDriver().findElement(submitButton).click();
    }

    public boolean isBoutonSoumettreDesactive() {
        return !Setup.getDriver().findElement(submitButton).isEnabled();
    }

    public String getMessageErreur() {
        return Setup.getDriver().findElement(errorMessage).getText();
    }

    public String getCouleurMessageErreur() {
        // Le fond rouge/rose (ex. rgb(248, 215, 218) pour Bootstrap ".alert-danger")
        // est la propriété la plus représentative de "s'affiche en rouge" :
        // c'est la zone visible la plus large, contrairement à la fine bordure.
        return Setup.getDriver().findElement(errorMessage).getCssValue("background-color");
    }

    /**
     * Indique si le message d'erreur s'affiche en rouge.
     * L'application utilise toujours Bootstrap ".alert-danger", dont le
     * fond est fixe : rgb(248, 215, 218) (ou rgba(248, 215, 218, 1) selon
     * le navigateur). On compare donc simplement à ces valeurs connues.
     */
    
    public boolean isMessageErreurEnRouge() {
        String couleurRgb = "rgb(248, 215, 218)";
        String couleurRgba = "rgba(248, 215, 218, 1)";
 
        String couleurReelle = getCouleurMessageErreur();
 
        return couleurReelle.equals(couleurRgb) || couleurReelle.equals(couleurRgba);
    
    }

    public boolean isConnexionReussie() {
        // Home.js redirige vers "/tasks" en cas de succès (history.replace('/tasks'))
        return Setup.getDriver().getCurrentUrl().contains("/tasks");
    }
}