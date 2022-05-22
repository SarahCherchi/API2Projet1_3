package ecole.gestion.modele;

import ecole.metier.Cours;

import java.util.ArrayList;
import java.util.List;

public class ModeleCours implements DAOCours{

    private List<Cours> mesCours=new ArrayList<>();
    @Override
    public Cours create(Cours newcr) {
        if(mesCours.contains(newcr)) return null;
        mesCours.add(newcr);
        return newcr;
    }

    @Override
    public Cours read(Cours crrech) {
        int idrech=crrech.getIdCours();
        for (Cours cr : mesCours) {
            if (cr.getIdCours()== idrech) {
                return cr;
            }
        }
        return null;
    }

    @Override
    public Cours update(Cours crrech) {
        Cours cr = read(crrech);
        if (cr == null) {
            return null;
        }
        cr.setCode(crrech.getCode());
        cr.setIntitule(crrech.getIntitule());
        cr.setSalleParDefault(crrech.getSalleParDefault());
        return cr;
    }

    @Override
    public boolean delete(Cours crrech){
        Cours cr = read(crrech);
        if(cr!=null) {
            mesCours.remove(cr);
            return true;
        }
        else return false;
    }

    @Override
    public List<Cours> readAll() {
        return mesCours;
    }

}
