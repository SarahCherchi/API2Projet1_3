package ecole.gestion.vue;

import ecole.metier.Classe;

import java.util.List;

public interface VueClasseInterface extends VueInterface<Classe, String> {

    void affHT(int HT);
    void affLobj(List lobj);
}
