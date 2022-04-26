package ecole.metier;

import java.util.Objects;

public class Infos {
    protected Cours cours;
    protected int nbrHeures;
    protected Salle salle;
    protected Enseignant enseignant;

    public Infos() {
    }

    public Infos(Cours cours) {
        this.cours = cours;
    }

    public Infos(int nbrHeures, Enseignant enseignant) {
        this.nbrHeures = nbrHeures;
        this.enseignant = enseignant;
    }

    public Infos(int nbrHeures, Salle salle) {
        this.nbrHeures = nbrHeures;
        this.salle = salle;
    }

    public Infos(Cours cours, int nbrHeures) {
        this.cours = cours;
        this.nbrHeures = nbrHeures;
    }

    public Infos(int nbrHeures) {
        this.nbrHeures = nbrHeures;
    }

    public Infos(Cours cours, int nbrHeures, Salle salle, Enseignant enseignant) {
        this.cours = cours;
        this.nbrHeures = nbrHeures;
        this.salle = salle;
        this.enseignant = enseignant;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public int getNbrHeures() {
        return nbrHeures;
    }

    public void setNbrHeures(int nbrHeures) {
        this.nbrHeures = nbrHeures;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    @Override
    public String toString() {
        return "Infos{" +
                "cours=" + cours +
                ", nbrHeures=" + nbrHeures +
                ", salle=" + salle +
                ", enseignant=" + enseignant +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Infos infos = (Infos) o;
        return Objects.equals(cours, infos.cours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cours);
    }
}
