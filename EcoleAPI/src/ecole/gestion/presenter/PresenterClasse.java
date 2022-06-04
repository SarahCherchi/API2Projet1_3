package ecole.gestion.presenter;

import ecole.gestion.modele.DAOClasse;
import ecole.gestion.vue.VueClasseInterface;
import ecole.metier.*;

public class PresenterClasse {
    private DAOClasse mdcl;
    private VueClasseInterface vuecl;
    private PresenterCours pc;
    private PresenterEnseignant pe;
    private PresenterSalle ps;


    public PresenterClasse(DAOClasse mdcl, VueClasseInterface vuecl) {
        this.mdcl = mdcl;
        this.vuecl = vuecl;
    }

    public void setPc(PresenterCours pc) {
        this.pc = pc;
    }

    public void setPe(PresenterEnseignant pe) {
        this.pe = pe;
    }

    public void setPs(PresenterSalle ps) {
        this.ps = ps;
    }

    public void gestion() {

        do {
            int ch = vuecl.menu(new String[]{"ajout","recherche","modification","suppression","ajout info","suppression info","affichage complet","fin"});
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
                   // addInfo();
                    break;
                case 6:
                   // suppInfo();
                    break;
                case 7:
                    affAll();
                    break;
                case 8:
                    return;
            }
        } while (true);

    }

    protected void ajout() {
        Classe newcl = vuecl.create();
        newcl = mdcl.create(newcl);
        if (newcl == null) {
            vuecl.displayMsg("erreur lors de la création de la classe - doublon");
            return;
        }
        vuecl.displayMsg("classe créée");
        vuecl.display(newcl);

    }
    protected Classe recherche() {

        String nrech = vuecl.read();
        Classe cl = new Classe(nrech, 0, "", 0);
        cl = mdcl.read(cl);
        if (cl == null) {
            vuecl.displayMsg("classe introuvable");
            return null;
        }
        vuecl.display(cl);
        return cl;
    }

    protected void modification() {
        Classe cl = recherche();
        if (cl != null) {
            cl = vuecl.update(cl);
            mdcl.update(cl);
        }
    }

    protected void suppression() {
        Classe cl = recherche();
        if (cl != null) {
            String rep;
            do {
                rep = vuecl.getMsg("confirmez-vous la suppression (o/n) ? ");

            } while (!rep.equalsIgnoreCase("o") && !rep.equalsIgnoreCase("n"));

            if (rep.equalsIgnoreCase("o")) {
                if (mdcl.delete(cl)) {
                    vuecl.displayMsg("classe supprimée");
                } else {
                    vuecl.displayMsg("classe non supprimée");
                }
            }

        }
    }

   /* private void addInfo() {
        Classe cl = recherche();
        if (cl == null) return;
        Cours c = pc.affAll();
        if (c == null) return;
        Salle s = ps.affAll();
        if (s == null) return;
        Enseignant e = pe.affAll();
        if (e == null) return;
        String nhs = vuecl.getMsg("nombres d'heures : ");
        int nh = Integer.parseInt(nhs);

        Infos i = new Infos(c, nh, s, e);
        boolean res = mdcl.addInfo(cl,i);

        if(res)vuecl.displayMsg("infos ajoutée");
        else vuecl.displayMsg("infos pas ajoutée");
    }

    private void suppInfo() {
        Classe cl = recherche();
        if (cl == null) return;
        Cours c = pc.affAll();
        if (c == null) return;
        Salle s = ps.affAll();
        if (s == null) return;
        Enseignant e = pe.affAll();
        if (e == null) return;

        Infos i = new Infos(c, 0, s, e);
        boolean res = mdcl.suppInfo(cl,i);

        if(res)vuecl.displayMsg("infos supprimée");
        else vuecl.displayMsg("infos pas supprimée");
    } */

    protected void affAll() {
        vuecl.affAll(mdcl.readAll());
    }

}

