package designpatterns.composite;

import ecole.metier.Cours;
import ecole.metier.Enseignant;
import ecole.metier.Infos;
import ecole.metier.Salle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Classe extends Element{
    private static int idClasseAct=0;

    protected int idClasse;
    protected String sigle;
    protected int annee;
    protected String specialite;
    protected int nbrEleves;
    protected List<Infos> info = new ArrayList<>();

    public Classe(int idClasse, String sigle, int annee, String specialite, int nbrEleves) {
        super(idClasse);
        this.idClasse = idClasse;
        this.sigle = sigle;
        this.annee = annee;
        this.specialite = specialite;
        this.nbrEleves = nbrEleves;
    }

    public static int getIdClasseAct() {
        return idClasseAct;
    }

    public static void setIdClasseAct(int idClasseAct) {
        Classe.idClasseAct = idClasseAct;
    }

    public int getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(int idClasse) {
        this.idClasse = idClasse;
    }

    public String getSigle() {
        return sigle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public int getNbrEleves() {
        return nbrEleves;
    }

    public void setNbrEleves(int nbrEleves) {
        this.nbrEleves = nbrEleves;
    }

    public List<Infos> getInfo() {
        return info;
    }

    public void setInfo(List<Infos> info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Classe classe = (Classe) o;
        return idClasse == classe.idClasse;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idClasse);
    }

    @Override
    public int totNbrEleves(){
        return nbrEleves;
    }

    @Override
    public String toString() {
        return "Classe{" +
                "idClasse=" + getIdClasse() +
                ", sigle='" + sigle + '\'' +
                ", annee=" + annee +
                ", specialite='" + specialite + '\'' +
                ", nbrEleves=" + nbrEleves +
                ", totNbrEleves=" + totNbrEleves() +
                '}';
    }
}




