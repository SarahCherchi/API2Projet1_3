package ecole.gestion.modele;

import ecole.metier.Classe;
import ecole.metier.Cours;

import java.util.List;

public interface DAOClasse extends DAO<Classe>{
    /*Classe create(Classe newcl);

    boolean delete(Classe clrech);

    Classe read(Classe clrech);

    Classe update(Classe clrech);

    List<Classe> readAll();*/

    boolean addCours(Classe cl, Cours c,int heures);
    //ajouter update et delete
}
