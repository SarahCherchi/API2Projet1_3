package ecole.metier;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Enseignant {
    protected int idEnseignant;
    protected String matricule;
    protected String nom;
    protected String prenom;
    protected String tel;
    protected int chargeSem;
    protected BigDecimal salaireMensu;
    protected LocalDate dateEngag;

    public Enseignant() {
    }

    public Enseignant(int idEnseignant, String nom) {
        this.idEnseignant = idEnseignant;
        this.nom = nom;
    }

    public Enseignant(String matricule, String nom, String prenom, String tel, int chargeSem, BigDecimal salaireMensu, LocalDate dateEngag) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.chargeSem = chargeSem;
        this.salaireMensu = salaireMensu;
        this.dateEngag = dateEngag;
    }

    public Enseignant(int idEnseignant, String matricule, String nom, String prenom, String tel, int chargeSem, BigDecimal salaireMensu, LocalDate dateEngag) {
        this.idEnseignant = idEnseignant;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.chargeSem = chargeSem;
        this.salaireMensu = salaireMensu;
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

    public int getChargeSem() {
        return chargeSem;
    }

    public void setChargeSem(int chargeSem) {
        this.chargeSem = chargeSem;
    }

    public BigDecimal getSalaireMensu() {
        return salaireMensu;
    }

    public void setSalaireMensu(BigDecimal salaireMensu) {
        this.salaireMensu = salaireMensu;
    }

    public LocalDate getDateEngag() {
        return dateEngag;
    }

    public void setDateEngag(LocalDate dateEngag) {
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
                ", salaireMensu=" + salaireMensu +
                ", dateEngag=" + dateEngag +
                '}';
    }
}
