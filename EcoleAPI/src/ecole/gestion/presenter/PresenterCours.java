package ecole.gestion.presenter;

import ecole.gestion.modele.DAOCours;
import ecole.gestion.vue.VueCoursInterface;
import ecole.metier.Cours;
import ecole.metier.Salle;

public class PresenterCours {
    private DAOCours mdc;
    private VueCoursInterface vuec;
    private PresenterSalle ps;

    public PresenterCours(DAOCours mdc, VueCoursInterface vuec) {
        this.mdc = mdc;
        this.vuec = vuec;
    }

    public void setPs(PresenterSalle ps){this.ps = ps;}

    public void gestion() {

        do {
            int ch = vuec.menu(new String[]{"ajout", "recherche unique", "modification", "suppression","voir tous", "fin"});
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
                case 6:
                    return;

            }
        } while (true);


    }

    protected void ajout() {
        Salle s = ps.recherche();
        Cours newcr = vuec.createSD(s);
        newcr = mdc.create(newcr);
        if (newcr == null) {
            vuec.displayMsg("erreur lors de la création du cours - doublon");
            return;
        }

        vuec.displayMsg("cours créé");
        vuec.display(newcr);

    }

    protected Cours recherche() {

        String nrechs = vuec.read();
        Cours cr = new Cours(nrechs,"",null);
        cr = mdc.read(cr);
        if (cr == null) {
            vuec.displayMsg("cours introuvable");
            return null;
        }
        vuec.display(cr);
        return cr;
    }

    protected void modification() {
        Cours cr = recherche();
        if (cr != null) {
            cr =  vuec.update(cr);
            mdc.update(cr);
        }
    }

    protected void suppression() {
        Cours cr = recherche();
        if (cr != null) {
            String rep;
            do {
                rep = vuec.getMsg("confirmez-vous la suppression (o/n) ? ");

            } while (!rep.equalsIgnoreCase("o") && !rep.equalsIgnoreCase("n"));

            if (rep.equalsIgnoreCase("o")) {
                if(mdc.delete(cr))  vuec.displayMsg("cours supprimé");
                else vuec.displayMsg("cours non supprimé");
            }

        }
    }

    protected void affAll() {
        vuec.affAll(mdc.readAll());
    }

}
