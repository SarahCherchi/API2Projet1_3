package ecole.gestion.presenter;

import ecole.gestion.modele.DAOSalle;
import ecole.gestion.vue.VueSalleInterface;
import ecole.metier.Salle;

public class PresenterSalle {

    private DAOSalle mds;
    private VueSalleInterface vues;


    public PresenterSalle(DAOSalle mds, VueSalleInterface vues) {
        this.mds = mds;
        this.vues = vues;

    }

    public void gestion() {

        do {
            int ch = vues.menu(new String[]{"ajout", "recherche unique", "modification", "suppression","voir tous","fin"});
            switch (ch) {
                case 1:
                    ajout();
                    break;
                case 2:
                    recherche();
                    break;
                case 3:
                    modification();
                    break;
                case 4:
                    suppression();
                    break;
                case 5:
                    affAll();
                    break;
                case 6 :  return;
            }
        } while (true);

    }

    protected void ajout() {

        Salle newsl = vues.create();
        newsl = mds.create(newsl);
        if (newsl == null) {
            vues.displayMsg("erreur lors de la création de la salle - doublon");
            return;
        }

        vues.displayMsg("salle créée");
        vues.display(newsl);

    }

    protected Salle recherche() {

        String nrech = vues.read();
        Salle sl = new Salle(nrech,0);
        sl = mds.read(sl);
        if (sl == null) {
            vues.displayMsg("salle introuvable");
            return null;
        }
        vues.display(sl);
        return sl;
    }

    protected void modification() {
        Salle sl = recherche();
        if (sl != null) {
            sl =  vues.update(sl);
            mds.update(sl);
        }
    }

    protected void suppression() {
        Salle sl = recherche();
        if (sl != null) {
            String rep;
            do {
                rep = vues.getMsg("confirmez-vous la suppression (o/n) ? ");

            } while (!rep.equalsIgnoreCase("o") && !rep.equalsIgnoreCase("n"));

            if (rep.equalsIgnoreCase("o")) {
                if( mds.delete(sl))vues.displayMsg("salle supprimée");
                else vues.displayMsg("salle non supprimée");
            }
        }
    }

    protected void affAll() {
        vues.affAll(mds.readAll());
    }
}
