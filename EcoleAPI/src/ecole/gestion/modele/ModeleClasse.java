package ecole.gestion.modele;

import ecole.metier.*;

import java.util.ArrayList;
import java.util.List;

public class ModeleClasse implements DAOClasse{

    private List<Classe> mesClasse=new ArrayList<>();

    @Override
    public Classe create(Classe newcl) {
        if(mesClasse.contains(newcl)) return null;
        mesClasse.add(newcl);
        return newcl;
    }

    @Override
    public Classe read(Classe clrech) {
        int idrech=clrech.getIdClasse();
        for (Classe cl : mesClasse) {
            if (cl.getIdClasse()== idrech) {
                return cl;
            }
        }
        return null;
    }

    @Override
    public Classe update(Classe clrech) {
        Classe cl = read(clrech);
        if (cl == null) {
            return null;
        }
        cl.setSigle(clrech.getSigle());
        cl.setAnnee(clrech.getAnnee());
        cl.setSpecialite(clrech.getSpecialite());
        cl.setNbrEleves(clrech.getNbrEleves());
        cl.setInfo(clrech.getInfo());
        return cl;
    }

    @Override
    public boolean delete(Classe clrech){
        Classe cl = read(clrech);
        if(cl!=null && cl.getInfo().isEmpty()) {
            mesClasse.remove(cl);
            return true;
        }
        else return false;
    }

    @Override
    public List<Classe> readAll() {
        return mesClasse;
    }

    @Override
    public int nbrHeuresTot(Classe cl) {
        return cl.nbrHeuresTot();
    }

    @Override
    public boolean addCours(Classe cl,Cours c, int heures) {
        return cl.addCours(c,heures);
    }

    @Override
    public boolean modifCoursE(Classe cl, Cours c, Enseignant ens) {
        return cl.modifCours(c,ens);
    }

    @Override
    public boolean modifCoursS(Classe cl, Cours c, Salle s) {
        return cl.modifCours(c,s);
    }

    @Override
    public boolean modifCoursH(Classe cl, Cours c, int heures) {
        return cl.modifCours(c,heures);
    }

    @Override
    public boolean suppCours(Classe cl, Cours c) {
        return cl.suppCours(c);
    }
}
