package ecole.gestion.vue;

import ecole.metier.Classe;
import ecole.metier.Infos;

import java.util.List;

public class VueClasse extends VueCommune implements VueClasseInterface{
    @Override
    public Classe create() {
        String sigle= getMsg("sigle : ");
        int annee = Integer.parseInt(getMsg("annee : "));
        String specialite = getMsg("specialite :");
        int nbrEleves = Integer.parseInt(getMsg("nombre d'eleves : "));
        Classe newcl = new Classe(sigle, annee, specialite, nbrEleves);
        return newcl;
    }


    @Override
    public void display(Classe cl) {
        displayMsg(cl.toString());
        if (!cl.getInfo().isEmpty()) {
            String rep;
            do {
                rep = getMsg("Afficher ses infos (o/n) ");
            } while (!rep.equalsIgnoreCase("o") && !rep.equalsIgnoreCase("n"));

            if (rep.equalsIgnoreCase("o")) {
                for (Infos infos : cl.getInfo()) {
                    displayMsg(infos.toString());
                }
            }
        }
    }


    @Override
    public Classe update(Classe cl) {

        do {
            String ch = getMsg("1.changement de nombre d'élèves\n2.fin");
            switch (ch) {
                case "1":
                    String nEleve = getMsg("nouveau nombre d'élèves :");
                    cl.setNbrEleves(Integer.parseInt(nEleve));
                    break;
                case "2":
                    return cl;
                default:
                    displayMsg("choix invalide");
            }

        } while (true);
    }


    @Override
    public Integer read() {
        String ns = getMsg("numéro de classe : ");
        int n = Integer.parseInt(ns);
        return n;
    }

    @Override
    public void affAll(List<Classe> lc) {
        int i =0;
        for(Classe cl:lc){
            displayMsg((++i)+"."+cl.toString());
        }
    }

}
