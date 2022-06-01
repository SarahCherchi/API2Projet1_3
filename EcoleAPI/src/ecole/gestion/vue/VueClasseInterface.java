package ecole.gestion.vue;

import ecole.metier.Classe;

import java.util.List;

public interface VueClasseInterface {
    int menu(String[] options);

    void displayMsg(String msg);

    String getMsg(String invite);

    Classe create();

    void display(Classe cl);

    Classe update(Classe cl);

    int read();

    void affAll(List<Classe> lc);
}
