package controleur;

import facadeCreaFilm.FacadeScreen;

import facadeCreaFilm.GenreNotFoundException;
import facadeCreaFilm.NomFilmDejaExistantException;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import modeleCreaFilm.Film;
import vues.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class Controleur implements Sujet{
    private Menu menu;
    private ListeFilms listeFilms;
    private AjoutFilm ajout;
    private FacadeScreen facadeScreen;
    private Collection<Observateur> observateurs;

    public Controleur(FacadeScreen facadeScreen, Stage stage) {
        this.facadeScreen = facadeScreen;
        this.observateurs = new ArrayList<>();
        menu = Menu.creerVue(this,stage);
        listeFilms = ListeFilms.creerVue(this,stage);
        ajout = AjoutFilm.creerVue(this,stage);

    }

    private void showMenu() {
        menu.show();
    }

    public void showFilms(){
        listeFilms.show();
    }

    public void showAjout(){
        ajout.show();
    }

    public void run() {
        showMenu();
    }

    public void gotoConsulter() {
        showFilms();
    }
    public void gotoAjout() {
        showAjout();
    }

    public void erreur (String message, String titre){
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle(titre);
        alert.showAndWait();

    }

    public void enregistrerFilm(String titreText, String realisateurText, String genreText) {
        try {
            this.facadeScreen.creerFilm(titreText,realisateurText,genreText);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Le film a bien été créé",ButtonType.OK);
            alert.showAndWait();
            this.notifierAll();
        } catch (NomFilmDejaExistantException e) {
            this.erreur("Erreur de création", "Nom de film déjà existant");
        } catch (GenreNotFoundException e) {
            this.erreur("Erreur de création", "Genre non trouvé");
        }
    }

    @Override
    public void ajouterUnObservateur(Observateur observateur) {
        this.observateurs.add(observateur);
    }

    @Override
    public void notifierAll() {
        this.observateurs.stream().forEach(e->e.notifier());
    }

    public Collection<Film> getFilms() {
        return this.facadeScreen.getAllFilms();
    }
/*
    public void gotoMenu() {
        showMenu();
    }

    /*


    public void creerFilm(String titre, String genre, String realisateur)  {
        try {
            facadeScreen.creerFilm(titre, realisateur,genre);
            showMenu();

        } catch (GenreNotFoundException e) {
            ajout.afficherErreur("Erreur de genre","Genre inexistant !");
            ajout.viderChamps();
            showAjout();
        }
        catch ( NomFilmDejaExistantException e) {
            ajout.afficherErreur("Erreur de film","Le titre du film existe déjà !");
            ajout.viderChamps();
            showAjout();
        }
}
     */
}
