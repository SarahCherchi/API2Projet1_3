package ecole.gestion.modole;

import ecole.metier.Cours;

import java.util.List;

public interface DAOCours {

    Cours create(Cours newcr);

    boolean delete(Cours crrech);

    Cours read(Cours crrech);

    Cours update(Cours crrech);

    List<Cours> readAll();
}
