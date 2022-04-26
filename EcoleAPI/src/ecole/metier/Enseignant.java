package ecole.metier;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Objects;

public class Enseignant {
    protected int idEnseignant;
    protected String matricule;
    protected String nom;
    protected String prenom;
    protected String tel;
    protected BigDecimal chargeSem;
    protected Date dateEngag;

    public Enseignant() {
    }

    public Enseignant(int idEnseignant, String matricule, String nom, String prenom, String tel, BigDecimal chargeSem, Date dateEngag) {
        this.idEnseignant = idEnseignant;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.chargeSem = chargeSem;
        chargeSem.setScale(2, RoundingMode.HALF_UP);
        this.dateEngag = dateEngag;
    }

    public int getIdEnseignant() {
        return idEnseignant;
    }

    public void setIdEnseignant(int idEnseignant) {
        this.idEnseignant = idEnseignant;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public BigDecimal getChargeSem() {
        return chargeSem;
    }

    public void setChargeSem(BigDecimal chargeSem) {
        this.chargeSem = chargeSem;
    }

    public Date getDateEngag() {
        return dateEngag;
    }

    public void setDateEngag(Date dateEngag) {
        this.dateEngag = dateEngag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enseignant that = (Enseignant) o;
        return idEnseignant == that.idEnseignant;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEnseignant);
    }

    @Override
    public String toString() {
        return "Enseignant{" +
                "idEnseignant=" + idEnseignant +
                ", matricule='" + matricule + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", tel='" + tel + '\'' +
                ", chargeSem=" + chargeSem +
                ", dateEngag=" + dateEngag +
                '}';
    }
}
