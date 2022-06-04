package ecole.gestion.vue;

import ecole.metier.Enseignant;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class VueEnseignant extends VueCommune implements VueEnseignantInterface{

    private Scanner sc = new Scanner(System.in);

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
        System.out.println("Date engagement (ex. 05 06 2022) : ");
        int j = sc.nextInt();
        int m = sc.nextInt();
        int a = sc.nextInt();
        sc.skip("\n");
        LocalDate dateEngag= LocalDate.of(a,m,j);
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
    public String read() {
        String n = getMsg("matricule de l'enseignant : ");
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
