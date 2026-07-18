package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import utils.Setup;

import java.util.List;

/**
 * Page Object représentant la page de gestion des tâches
 * (composant src/components/Tasks.js, route "/tasks").
 *
 * ATTENTION (comportements réels du code à connaître) :
 * - Le bouton "Ajouter la tâche" n'a jamais d'attribut disabled.
 * - deleteTask() et setCompleted() ne mettent à jour QUE l'état React,
 *   jamais le localStorage (seul addTask() le fait).
 * - Une tâche s'affiche sous la forme "Nom : description", pas "Nom description".
 */
public class TasksPage {


    // Les deux champs texte n'ont ni id ni name : on les cible par position.
    private final By champNomTache = By.xpath("(//input[@class='form-control'])[1]");
    private final By champDescriptionTache = By.xpath("(//input[@class='form-control'])[2]");
    private final By boutonAjouterTache = By.xpath("//button[text()='Ajouter la tâche']");


    // ---- Formulaire d'ajout ----

    public void saisirNomTache(String nom) {
        Setup.getDriver().findElement(champNomTache).clear();
        Setup.getDriver().findElement(champNomTache).sendKeys(nom);
    }

    public void saisirDescriptionTache(String description) {
    	Setup.getDriver().findElement(champDescriptionTache).clear();
        Setup.getDriver().findElement(champDescriptionTache).sendKeys(description);
    }

    public void cliquerSurBoutonAjouter() {
    	Setup.getDriver().findElement(boutonAjouterTache).click();
    }

    public boolean isBoutonAjouterDesactive() {
        return !Setup.getDriver().findElement(boutonAjouterTache).isEnabled();
    }

    // ---- Liste des tâches ----

    /**
     * Cherche, parmi toutes les lignes de tâches affichées, une ligne
     * dont le texte contient à la fois le nom et la description donnés.
     */
    public boolean isTacheAffichee(String nom, String description) {
        List<WebElement> lignes = Setup.getDriver().findElements(By.cssSelector(".list-group-item"));
        for (WebElement ligne : lignes) {
            String texte = ligne.getText();
            if (texte.contains(nom) && texte.contains(description)) {
                return true;
            }
        }
        return false;
    }

    public boolean isTacheSupprimee(String nom) {
        return trouverLigneTache(nom).isEmpty();
    }

    public void marquerTacheCommeTerminee(String nom) {
        WebElement ligne = trouverLigneTache(nom).get(0);
        ligne.findElement(By.cssSelector(".badge")).click();
    }

    /** Retourne le texte réel du badge : "Complétée" ou "Non complétée". */
    public String getStatusTache(String nom) {
        WebElement ligne = trouverLigneTache(nom).get(0);
        return ligne.findElement(By.cssSelector(".badge")).getText();
    }

    public void supprimerTache(String nom) {
        WebElement ligne = trouverLigneTache(nom).get(0);
        ligne.findElement(By.linkText("Supprimer")).click();
    }

    private List<WebElement> trouverLigneTache(String nom) {
        return Setup.getDriver().findElements(By.xpath("//li[contains(., '" + nom + "')]"));
    }

    // ---- localStorage ----

    /**
     * Ajoute une tâche directement dans le localStorage (sans passer par
     * l'UI) puis recharge la page pour que React affiche l'état à jour.
     * Utile pour les steps "Given une tâche ... existe".
     */
    public void ajouterTacheDirectementViaLocalStorage(String nom, String description) {
        JavascriptExecutor js = (JavascriptExecutor) Setup.getDriver();
        String script =
                "var tasks = JSON.parse(window.localStorage.getItem('tasks')) || [];" +
                "tasks.push({name: arguments[0], description: arguments[1], completed: false});" +
                "window.localStorage.setItem('tasks', JSON.stringify(tasks));";
        js.executeScript(script, nom, description);
        Setup.getDriver().navigate().refresh();
    }

    public boolean isLocalStorageVide() {
        String tasksJson = lireTasksDepuisLocalStorage();
        return tasksJson == null || tasksJson.equals("[]");
    }

    public boolean localStorageContientTache(String nom, String description) {
        String tasksJson = lireTasksDepuisLocalStorage();
        return tasksJson != null
                && tasksJson.contains("\"name\":\"" + nom + "\"")
                && tasksJson.contains("\"description\":\"" + description + "\"");
    }

    public boolean localStorageContientTacheTerminee(String nom, String description) {
        String tasksJson = lireTasksDepuisLocalStorage();
        String tacheAttendue = "\"name\":\"" + nom + "\",\"description\":\"" + description + "\",\"completed\":true";
        return tasksJson != null && tasksJson.contains(tacheAttendue);
    }

    public boolean localStorageNeContientPlusTache(String nom) {
        String tasksJson = lireTasksDepuisLocalStorage();
        return tasksJson == null || !tasksJson.contains("\"name\":\"" + nom + "\"");
    }

    private String lireTasksDepuisLocalStorage() {
        JavascriptExecutor js = (JavascriptExecutor) Setup.getDriver();
        return (String) js.executeScript("return window.localStorage.getItem('tasks');");
    }
}