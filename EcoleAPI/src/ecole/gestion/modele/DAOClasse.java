package ecole.gestion.modele;

import ecole.metier.Classe;
import ecole.metier.Cours;
import ecole.metier.Infos;

import java.util.List;

public interface DAOClasse extends DAO<Classe>{
    //boolean addCours(Classe rechcl, Cours c, int heures);
    //boolean salleCapaciteOK(Classe clrech);
    int nbrHeuresTot(Classe cl);
}
