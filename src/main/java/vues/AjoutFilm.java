package vues;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import modeleCreaFilm.Film;

import java.io.Console;
import java.io.IOException;
import java.net.URL;

public class AjoutFilm implements VueInteractive {

    private Stage stage;
    private Scene scene;
    private Controleur controleur;

    @FXML
    private TextField titre;
    @FXML
    private TextField genre;
    @FXML
    private TextField realisateur;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public static AjoutFilm creerVue(Controleur controleur, Stage stage){
        URL location = AjoutFilm.class.getResource("AjoutFilm.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);

        try {
            BorderPane borderPane = fxmlLoader.load();

            AjoutFilm vue = fxmlLoader.getController();
            vue.setStage(stage);
            vue.setScene(new Scene(borderPane,600,700));
            vue.setControleur(controleur);
            return vue;
        } catch (IOException e) {
            throw new RuntimeException("Problème FXML : AjoutFilm.fxml");
        }
    }

    @Override
    public void setControleur(Controleur controleur) {
        this.controleur = controleur;

    }

    @Override
    public void show() {
        this.stage.setScene(this.scene);
        this.stage.show();

    }

    public void enregistrer(ActionEvent actionEvent) {
        String titreText = this.titre.getText();
        String genreText = this.genre.getText();
        String realisateurText = this.realisateur.getText();

        if(!titreText.isEmpty() && !genreText.isEmpty() && !realisateurText.isEmpty()){
            this.controleur.enregistrerFilm(titreText, realisateurText, genreText);
        }
    }

    public void menu(ActionEvent actionEvent) {
        this.controleur.run();
    }
}
