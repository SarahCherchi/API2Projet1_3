package ecole.gestion.vue;

import ecole.metier.Enseignant;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class VueEnseignant extends VueCommune implements VueEnseignantInterface{
    @Override
    public Enseignant create() {
        String matricule = getMsg("matricule : ");
        String nom = getMsg("nom : ");
        String prenom = getMsg("prénom : ");
        String tel = getMsg("téléphone : ");
        String chargesem = getMsg("charge semistrielle :");
        int chargeSem = Integer.parseInt(chargesem);
        String salairemensu = getMsg(" salaire mensuelle : ");
        BigDecimal salaireMensu = new BigDecimal(salairemensu);
        String dateengag = getMsg("date engagement : ");
        Date dateEngag = new Date(dateengag); //voir format date A
        Enseignant newens = new Enseignant(matricule,nom,prenom,tel,chargeSem,salaireMensu,dateEngag);
        return newens;
    }


    @Override
    public void display(Enseignant ens) {
            displayMsg(ens.toString());
    }

    @Override
    public Enseignant update(Enseignant ens) {

        do {
            String ch = getMsg("1.changement de salaire\n2.fin");
            switch (ch) {
                case "1":
                    String nsalmensu = getMsg("nouveau salaire :");
                    BigDecimal nsalMensu  = new BigDecimal(nsalmensu);
                    ens.setSalaireMensu(nsalMensu);
                    break;
                case "2":
                    return ens;
                default:
                    displayMsg("choix invalide");
            }

        } while (true);
    }


    @Override
    public Integer read() {
        String ns = getMsg("numero de l'enseignant : ");
        int n = Integer.parseInt(ns);
        return n;
    }

    @Override
    public void affAll(List<Enseignant> le) {
        int i =0;
        for(Enseignant ens:le){
            displayMsg((++i)+"."+ens.toString());
        }
    }

}
