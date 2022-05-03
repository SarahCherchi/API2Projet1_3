package designpatterns.composite;

import ecole.metier.Cours;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Salle {
    protected int idSalle;
    protected String sigle;
    protected int capacité;
    protected List<ecole.metier.Cours> cours = new ArrayList<>();

    public Salle() {
    }

    public Salle(int idSalle, String sigle, int capacité) {
        this.idSalle = idSalle;
        this.sigle = sigle;
        this.capacité = capacité;
    }

    public int getIdSalle() {
        return idSalle;
    }

    public void setIdSalle(int idSalle) {
        this.idSalle = idSalle;
    }

    public String getSigle() {
        return sigle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public int getCapacité() {
        return capacité;
    }

    public void setCapacité(int capacité) {
        this.capacité = capacité;
    }

    public List<ecole.metier.Cours> getCours() {
        return cours;
    }

    public void setCours(List<Cours> cours) {
        this.cours = cours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Salle salle = (Salle) o;
        return idSalle == salle.idSalle;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSalle);
    }

    @Override
    public String toString() {
        return "Salle{" +
                "idSalle=" + idSalle +
                ", sigle='" + sigle + '\'' +
                ", capacité=" + capacité +
                '}';
    }
}
