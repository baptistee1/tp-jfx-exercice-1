package controleur;

public interface Sujet {
    void ajouterUnObservateur(Observateur observateur);
    void notifierAll();
}
