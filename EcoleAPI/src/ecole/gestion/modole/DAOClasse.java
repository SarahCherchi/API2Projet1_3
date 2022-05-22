package ecole.gestion.modole;

import ecole.metier.Classe;
import ecole.metier.Cours;

import java.util.List;

public interface DAOClasse {
    Classe create(Classe newcl);

    boolean delete(Classe clrech);

    Classe read(Classe clrech);

    Classe update(Classe clrech);

    boolean addCours(Classe cl, Cours c,int heures);

    List<Classe> readAll();
}
