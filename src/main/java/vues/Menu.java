package vues;

import controleur.Controleur;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Menu implements VueInteractive {

    private Stage stage;
    private Scene scene;
    private BorderPane borderPane;
    private Button consulterFilms;
    private Button ajouterFilms;
    private Controleur controleur;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    private Menu(){
    }

    public static Menu creerVue(Controleur controleur, Stage stage){
        Menu menu = new Menu();
        menu.setStage(stage);
        menu.initialiserComposants();
        menu.setControleur(controleur);
        return menu;
    }

    private void initialiserComposants() {
        this.borderPane = new BorderPane();
        this.consulterFilms = new Button("Consulter");
        this.ajouterFilms = new Button("Ajouter");

        this.consulterFilms.setOnAction(e->controleur.gotoConsulter());
        this.ajouterFilms.setOnAction(e->controleur.gotoAjout());

        this.consulterFilms.setMaxWidth(Double.MAX_VALUE);
        this.ajouterFilms.setMaxWidth(Double.MAX_VALUE);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        vBox.getChildren().addAll(this.ajouterFilms,this.consulterFilms);
        this.borderPane.setCenter(vBox);

        Label label = new Label("Menu");
        label.setFont(Font.font(32));
        this.borderPane.setTop(label);

        BorderPane.setAlignment(label,Pos.CENTER);
        BorderPane.setAlignment(vBox,Pos.CENTER);

        this.setScene(new Scene(this.borderPane, 600, 700));

    }


    @Override
    public void setControleur(Controleur controleur) {
        this.controleur = controleur;

    }

    public void show() {
        this.stage.setScene(this.scene);
        this.stage.show();
    }
}
