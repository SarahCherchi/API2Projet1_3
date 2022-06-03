package ecole.gestion.modele;

import ecole.metier.Classe;
import ecole.metier.Infos;

public interface DAOClasse extends DAO<Classe>{
    boolean addInfo(Classe cl, Infos i);
    boolean suppInfo(Classe cl, Infos i);

}
