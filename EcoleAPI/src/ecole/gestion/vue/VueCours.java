package ecole.gestion.vue;


import ecole.metier.Cours;
import ecole.metier.Salle;


import java.util.List;

public class VueCours extends VueCommune implements VueCoursInterface {

    @Override
    public Cours createSD(Salle s) {
        String code = getMsg("code cours : ");
        String intitulé = getMsg("intitulé: ");
        Cours newcr = new Cours(code, intitulé,s);
        return newcr;
    }

    @Override
    public Cours create() {
        return null; //pour le constructeur
    }


    @Override
    public void display(Cours cr) {
        displayMsg(cr.toString());
    }


    @Override
    public Cours update(Cours cr) {

        do {
            String ch = getMsg("1.changement de code\n2.nouveau intitulé\n3.fin");
            switch (ch) {
                case "1":
                    String code = getMsg("nouveau code : ");
                    cr.setCode(code);
                    break;
                case "2":
                    String intitulé = getMsg("nouveau intitulé : ");
                    cr.setIntitule(intitulé);
                    break;
                case "3":
                    return cr;
                default:
                    displayMsg("choix invalide");
            }

        } while (true);
    }


    @Override
    public String read() {
        String code = getMsg("code du cours :");
        return code;
    }

    @Override
    public void affAll(List<Cours> lcr) {
        int i =0;
        for(Cours c :lcr){
            displayMsg((++i)+"."+c.toString());
        }
    }
}
