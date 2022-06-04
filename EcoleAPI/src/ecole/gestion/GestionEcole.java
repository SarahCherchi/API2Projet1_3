package ecole.gestion;

import ecole.gestion.modele.*;
import ecole.gestion.presenter.PresenterClasse;
import ecole.gestion.presenter.PresenterCours;
import ecole.gestion.presenter.PresenterEnseignant;
import ecole.gestion.presenter.PresenterSalle;
import ecole.gestion.vue.*;

import java.util.Scanner;

public class GestionEcole {
    private static Scanner sc = new Scanner(System.in);

    private PresenterCours pc;
    private PresenterClasse pcl;
    private PresenterEnseignant pe;
    private PresenterSalle ps;

    public void gestion(String modeVue,String modeData) {

        VueClasseInterface vuecl;
        VueCoursInterface vuec;
        VueEnseignantInterface vuee;
        VueSalleInterface vues;
        VueCommuneInterface vcm;

        DAOCours mdc;
        DAOClasse mdcl;
        DAOEnseignant mde;
        DAOSalle mds;

        if (modeVue.equals("console")){
            vuec = new VueCours();
            vuecl=new VueClasse();
            vuee = new VueEnseignant();
            vues=new VueSalle();
            vcm=new VueCommune();

        }
        else {
            vuec = null;//new VueCours();
            vuecl=null;//new VueClasse();
            vuee = null;//new VueEnseignant();
            vues=null;//new VueSalle();
            vcm=null;//new VueCommune();
        }

        if(modeData.equals("db")){
            mdc= new ModeleCoursDB();
            mdcl = new ModeleClasseDB();
            mde = new ModeleEnseignantDB();
            mds = new ModeleSalleDB();
        }
        else {
            mdc= new ModeleCours();
            mdcl = new ModeleClasse();
            mde = new ModeleEnseignant();
            mds = new ModeleSalle();
        }


        pe = new PresenterEnseignant(mde, vuee);
        ps = new PresenterSalle(mds, vues);
        pc = new PresenterCours(mdc, vuec);
        pcl = new PresenterClasse(mdcl, vuecl);

        pcl.setPc(pc);
        pcl.setPe(pe);
        pcl.setPs(ps);
        pc.setPs(ps);

        do {

            int ch = vcm.menu(new String[]{"Enseignants","Salles","Cours","Classes","fin"});

            switch (ch) {
                case 1:
                    pe.gestion();
                    break;
                case 2:
                    ps.gestion();
                    break;
                case 3:
                    pc.gestion();
                    break;
                case 4:
                    pcl.gestion();
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("choix invalide");
            }
        } while (true);
    }

    public static void main(String[] args) {
        String  modeVue = args[0];
        String modeData= args[1];
        GestionEcole gm = new GestionEcole();
        gm.gestion(modeVue,modeData);
    }

}

