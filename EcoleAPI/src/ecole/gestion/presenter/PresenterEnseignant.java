package ecole.gestion.presenter;

import ecole.gestion.modele.DAOEnseignant;
import ecole.gestion.vue.VueEnseignantInterface;
import ecole.metier.Enseignant;

import java.math.BigDecimal;
import java.util.List;

public class PresenterEnseignant {

    private DAOEnseignant mde;
    private VueEnseignantInterface vuee;

    public PresenterEnseignant(DAOEnseignant mde, VueEnseignantInterface vuee) {
        this.mde = mde;
        this.vuee = vuee;
    }

    public void gestion() {

        do {
            int ch = vuee.menu(new String[]{"ajout", "recherche unique", "modification", "suppression","voir tous", "fin"});
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

        Enseignant newens = vuee.create();
        newens = mde.create(newens);
        if (newens == null) {
            vuee.displayMsg("erreur lors de la création de l'enseigant - doublon");
            return;
        }

        vuee.displayMsg("enseignant créé");
        vuee.display(newens);

    }

    protected Enseignant recherche() {

        String nrechs = vuee.read();
        Enseignant ens = new Enseignant(nrechs,"","","",0,new BigDecimal(0),null);
        ens = mde.read(ens);
        if (ens == null) {
            vuee.displayMsg("enseignant introuvable");
            return null;
        }
        vuee.display(ens);
        return ens;
    }

    protected void modification() {
        Enseignant ens = recherche();
        if (ens != null) {
            ens =  vuee.update(ens);
            mde.update(ens);
        }
    }

    protected void suppression() {
        Enseignant ens = recherche();
        if (ens != null) {
            String rep;
            do {
                rep = vuee.getMsg("confirmez-vous la suppression (o/n) ? ");

            } while (!rep.equalsIgnoreCase("o") && !rep.equalsIgnoreCase("n"));

            if (rep.equalsIgnoreCase("o")) {
                if(mde.delete(ens))  vuee.displayMsg("enseignant supprimé");
                else vuee.displayMsg("enseignant non supprimé");
            }

        }
    }

    protected void affAll() {
        vuee.affAll(mde.readAll());
    }

}