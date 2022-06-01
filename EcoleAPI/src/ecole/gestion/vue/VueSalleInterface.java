package ecole.gestion.vue;

import ecole.metier.Salle;

import java.util.List;

public interface VueSalleInterface {
    int menu(String[] options);

    void displayMsg(String msg);

    String getMsg(String invite);

    Salle create();

    void display(Salle sl);

    Salle update(Salle sl);

    int read();

    void affAll(List<Salle> lsl);
}
