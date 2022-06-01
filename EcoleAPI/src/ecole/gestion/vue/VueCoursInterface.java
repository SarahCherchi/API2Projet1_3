package ecole.gestion.vue;

import ecole.metier.Cours;

import java.util.List;

public interface VueCoursInterface {
    int menu(String[] options);

    void displayMsg(String msg);

    String getMsg(String invite);

    Cours create();

    void display(Cours cs);

    Cours update(Cours cs);

    int read();

    void affAll(List<Cours> lcs);
}
