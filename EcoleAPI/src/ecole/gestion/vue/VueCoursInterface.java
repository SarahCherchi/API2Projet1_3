package ecole.gestion.vue;

import ecole.metier.Cours;
import ecole.metier.Salle;


public interface VueCoursInterface extends VueInterface<Cours,String>{
    Cours createSD(Salle s);
}
