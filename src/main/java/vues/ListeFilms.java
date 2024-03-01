package vues;

import controleur.Controleur;
import controleur.Observateur;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import modeleCreaFilm.Film;

import java.io.Console;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Objects;

public class ListeFilms implements VueInteractive, Observateur {

    private Stage stage;
    private Scene scene;
    private Controleur controleur;

    @FXML
    ListView<Film> listeFilms;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public static
    ListeFilms creerVue(Controleur controleur,Stage stage){
        URL location = ListeFilms.class.getResource("ListeFilms.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);

        try {
            BorderPane borderPane = fxmlLoader.load();

            ListeFilms vue = fxmlLoader.getController();
            vue.setStage(stage);
            vue.setScene(new Scene(borderPane,600,700));
            controleur.ajouterUnObservateur(vue);
            vue.setControleur(controleur);
            vue.costumiserListView();
            return vue;
        } catch (IOException e) {
            throw new RuntimeException("Problème FXML : ListeFilms.fxml");
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

    public void retour(ActionEvent actionEvent) {
        this.controleur.run();
    }

    @Override
    public void notifier() {
        Collection<Film> films = this.controleur.getFilms();
        this.listeFilms.getItems().clear();
        this.listeFilms.refresh();
        this.listeFilms.setItems(FXCollections.observableArrayList(films));
        this.listeFilms.refresh();
    }

    public void costumiserListView(){
        this.listeFilms.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Film> call(ListView<Film> filmListView) {
                return new ListCell<>(){
                    @Override
                    protected void updateItem(Film film, boolean b) {
                        if(!Objects.isNull(film)){
                            this.setText(film.getId() + " - " + film.getTitre() + " - " + film.getGenre() + " - " + film.getRealisateur());
                        }
                    }
                };
            }
        });

    }
}
