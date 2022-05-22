package ecole.gestion.modole;

import ecole.metier.Salle;

import java.util.List;

public interface DAOSalle {
    Salle create(Salle newsl);

    boolean delete(Salle slrech);

    Salle read(Salle slrech);

    Salle update(Salle slrech);

    List<Salle> readAll();
}
