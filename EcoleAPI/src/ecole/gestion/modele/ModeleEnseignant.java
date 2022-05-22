package ecole.gestion.modele;

import ecole.metier.Enseignant;

import java.util.ArrayList;
import java.util.List;

public class ModeleEnseignant implements DAOEnseignant {
    private List<Enseignant> mesEnseignant = new ArrayList<>();

    @Override
    public Enseignant create(Enseignant newens) {
        if (mesEnseignant.contains(newens)) return null;
        mesEnseignant.add(newens);
        return newens;
    }

    @Override
    public Enseignant read(Enseignant ensrech) {
        int idrech = ensrech.getIdEnseignant();
        for (Enseignant ens : mesEnseignant) {
            if (ens.getIdEnseignant() == idrech) {
                return ens;
            }
        }
        return null;
    }

    @Override
    public Enseignant update(Enseignant ensrech) {
        Enseignant ens = read(ensrech);
        if (ens == null) {
            return null;
        }
        ens.setMatricule(ensrech.getMatricule());
        ens.setNom(ensrech.getNom());
        ens.setPrenom(ensrech.getPrenom());
        ens.setTel(ensrech.getTel());
        ens.setChargeSem(ensrech.getChargeSem());
        ens.setDateEngag(ensrech.getDateEngag());
        return ens;
    }

    @Override
    public boolean delete(Enseignant ensrech) {
        Enseignant ens = read(ensrech);
        if (ens != null) {
            mesEnseignant.remove(ens);
            return true;
        } else return false;
    }

    @Override
    public List<Enseignant> readAll() {
        return mesEnseignant;
    }
}
