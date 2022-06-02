package ecole.gestion.modele;

import ecole.metier.Classe;
import ecole.metier.Cours;

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

}
