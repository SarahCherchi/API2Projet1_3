package ecole.gestion.modole;

import ecole.metier.Salle;

import java.util.ArrayList;
import java.util.List;

public class ModeleSalle implements DAOSalle{

    private List<Salle> mesSalles = new ArrayList<>();

    @Override
    public Salle create(Salle newsl) {
        mesSalles.add(newsl);
        return newsl;
    }

    @Override
    public boolean delete(Salle slrech) {
        return false;
    }

    @Override
    public Salle read(Salle slrech) {
        return null;
    }

    @Override
    public Salle update(Salle slrech) {
        return null;
    }

    @Override
    public List<Salle> readAll() {
        return null;
    }
}
