package ecole.metier;

import java.util.Objects;

public class Cours {
    private static int idCoursAct = 0;
    protected int idCours;
    protected String code;
    protected String intitule;
    protected Salle salleParDefault;

    public Cours() {
    }

    public Cours(String code, String intitule, Salle salleParDefault) {
        this.code = code;
        this.intitule = intitule;
        this.salleParDefault = salleParDefault;
    }

    public Cours(int idCours, String code, String intitule) {
        this.idCours = idCours;
        this.code = code;
        this.intitule = intitule;
    }

    public Cours(String code, String intitule) {
        this.code = code;
        this.intitule = intitule;
    }

    public Cours(int idCours, String code, String intitule, Salle salleParDefault) {
        this.idCours = idCours;
        this.code = code;
        this.intitule = intitule;
        this.salleParDefault = salleParDefault;
    }

    public Cours(int idCours, Salle salleParDefault) {
        this.idCours = idCours;
        this.salleParDefault = salleParDefault;
    }

    public int getIdCours() {
        return idCours;
    }

    public void setIdCours(int idCours) {
        this.idCours = idCours;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public Salle getSalleParDefault() {
        return salleParDefault;
    }

    public void setSalleParDefault(Salle salleParDefault) {
        this.salleParDefault = salleParDefault;
    }

    @Override
    public String toString() {
        return "Cours{" +
                "idCours=" + idCours +
                ", code='" + code + '\'' +
                ", intitule='" + intitule + '\'' +
                ", salleParDefault=" + salleParDefault +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cours cours = (Cours) o;
        return idCours == cours.idCours;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCours);
    }
}