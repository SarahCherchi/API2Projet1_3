package ecole.gestion.presenter;

import ecole.gestion.modele.DAOClasse;
import ecole.gestion.vue.VueClasseInterface;
import ecole.metier.*;

import java.util.List;

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
            int ch = vuecl.menu(new String[]{"ajout","recherche","modification","suppression","affichage complet","nombre d'heure total","vérif capacité de la salle","plus d'affichage","plus d'option","fin"});
            switch (ch) {
                case 1:
                    ajout();
                    break;
                case 2:
                    recherche(); //CORRIGÉ
                    break;
                case 3:
                    modification(); //CORRIGÉ
                    break;
                case 4:
                    suppression(); //CORRIGÉ
                    break;
                case 5:
                    affAll();
                    break;
                case 6:
                    nbrHeureTot();
                    break;
                case 7:
                    //salleCapaciteOK();
                    break;
                case 8:
                    affichSupp();
                    break;
                case 9:
                    //optionSupp();
                    break;
                case 10:
                    return;
            }
        } while (true);

    }

    private void affichSupp() {
        Classe cl = recherche();

        if (cl != null) {
            do {
                List l=null;
                int ch = vuecl.menu(new String[]{"ajout cours", "liste enseignant et heures" ,"liste salle et heures","liste cours et heures","fin"});
                switch (ch) {
                    case 1:
                       // l = cl.addCours();
                        break;
                    case 2:
                        l = cl.listeenseignantsEtHeures();
                        break;
                    case 3:
                        l= cl.listeSallesetHeures();
                        break;
                    case 4:
                        l=cl.listeCoursEtHeures();
                        break;
                    case 5:
                        return;
                }
                if(l==null) {
                    vuecl.displayMsg("une erreur s'est produite");
                    continue;
                }
                if(l.isEmpty()) vuecl.displayMsg("aucun élément à afficher");
                else vuecl.affLobj(l);
            }

            while (true) ;
        }
    }

   /* private void optionSupp() {

    }*/

  protected void nbrHeureTot(){
        String nrech = vuecl.read();
        Classe cl = new Classe(0,nrech,0,"",0);
        cl = mdcl.read(cl);
        if (cl == null) {
            vuecl.displayMsg("introuvable");
        }
        else{
            int HT = mdcl.nbrHeuresTot(cl);
            vuecl.affHT(HT);
        }

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
        Classe cl = new Classe(0,nrech, 0,"", 0,null);
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

    protected void affAll() {
        vuecl.affAll(mdcl.readAll());
    }
}

