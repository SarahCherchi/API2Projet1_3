package ecole.gestion.vue;

import java.util.List;

public interface VueCommuneInterface {
    int menu(String[] options);

    void displayMsg(String msg);

    String getMsg(String invite);
}
