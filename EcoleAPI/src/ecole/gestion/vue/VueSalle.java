package ecole.gestion.vue;

import ecole.metier.Cours;
import ecole.metier.Salle;

import java.util.List;

public class VueSalle extends VueCommune implements VueSalleInterface{

    @Override
    public Salle create() {
        String sigle = getMsg("sigle : ");
        Integer capacité = Integer.parseInt(getMsg("capacité: "));
        Salle newsl = new Salle(sigle, capacité);
        return newsl;
    }

    @Override
    public void display(Salle sl) {
        displayMsg(sl.toString());
        if (!sl.getCours().isEmpty()) {
            String rep;
            do {
                rep = getMsg("Afficher les cours (o/n) ");
            } while (!rep.equalsIgnoreCase("o") && !rep.equalsIgnoreCase("n"));

            if (rep.equalsIgnoreCase("o")) {
                for (Cours cr : sl.getCours()) {
                    displayMsg(cr.toString());
                }
            }
        }
    }


    @Override
    public Salle update(Salle sl) {

        do {
            String ch = getMsg("1.changement capacité\n2.fin");
            switch (ch) {
                case "1":
                    String capacite = getMsg("nouvelle capacité:");
                    int ncap = Integer.parseInt(capacite);
                    sl.setCapacité(ncap);
                    break;
                case "2":
                    return sl;
                default:
                    displayMsg("choix invalide");
            }

        } while (true);
    }

    @Override
    public String read() {
        String sigle = getMsg("sigle de la salle : ");
        return sigle;
    }

    @Override
    public void affAll(List<Salle> lsl) {
        int i =0;
        for(Salle sl:lsl){
            displayMsg((++i)+"."+sl.toString());
        }
    }
}
