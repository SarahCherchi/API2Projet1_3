package ecole.gestion.vue;

import ecole.metier.Enseignant;

import java.util.List;

public interface VueEnseignantInterface {
    int menu(String[] options);

    void displayMsg(String msg);

    String getMsg(String invite);

    Enseignant create();

    void display(Enseignant ens);

    Enseignant update(Enseignant ens);

    int read();

    void affAll(List<Enseignant> lens);
}
