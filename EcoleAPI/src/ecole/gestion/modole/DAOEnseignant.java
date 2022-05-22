package ecole.gestion.modole;

import ecole.metier.Enseignant;

import java.util.List;

public interface DAOEnseignant {
    Enseignant create(Enseignant newens);

    boolean delete(Enseignant ensrech);

    Enseignant read(Enseignant ensrech);

    Enseignant update(Enseignant ensrech);

    List<Enseignant> readAll();
}
