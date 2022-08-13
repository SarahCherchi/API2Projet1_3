package ecole.metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Classe {
    private static int idClasseAct=0;

    protected int idClasse;
    protected String sigle;
    protected int annee;
    protected String specialite;
    protected int nbrEleves;
    protected List<Infos> info = new ArrayList<>();

    public Classe() {
    }

    public Classe(String sigle, int annee, String specialite, int nbrEleves, List<Infos> info) {
        this.sigle = sigle;
        this.annee = annee;
        this.specialite = specialite;
        this.nbrEleves = nbrEleves;
        this.info = info;
    }

    public Classe(String sigle, int annee, String specialite, int nbrEleves) {
        this.sigle = sigle;
        this.annee = annee;
        this.specialite = specialite;
        this.nbrEleves = nbrEleves;
    }

    public Classe(int idClasse, String sigle, int annee) {
        this.idClasse = idClasse;
        this.sigle = sigle;
        this.annee = annee;
    }

    public Classe(int idClasse, String sigle, int annee, String specialite, int nbrEleves) {
        this.idClasse = idClasse;
        this.sigle = sigle;
        this.annee = annee;
        this.specialite = specialite;
        this.nbrEleves = nbrEleves;
    }

    public Classe(int idClasse, String sigle, int annee, String specialite, int nbrEleves, List<Infos> info) {
        this.idClasse = idClasse;
        this.sigle = sigle;
        this.annee = annee;
        this.specialite = specialite;
        this.nbrEleves = nbrEleves;
        this.info = info;
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
    public String toString() {
        return "Classe{" +
                "idClasse=" + idClasse +
                ", sigle='" + sigle + '\'' +
                ", annee=" + annee +
                ", specialite='" + specialite + '\'' +
                ", nbrEleves=" + nbrEleves +
                ", info=" + info +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classe classe = (Classe) o;
        return idClasse == classe.idClasse;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClasse);
    }

    public int nbrHeuresTot(){
            int heuresTotal=0;
            for(Infos i : info){
                heuresTotal+=i.getNbrHeures();
            }
            return heuresTotal;
        }

    public List<Infos> listeenseignantsEtHeures(){
        List<Infos> lEetH = new ArrayList<>();
        if(info.isEmpty())
        {
            return null;
        }
        for(Infos i : info){
            lEetH.add(new Infos(i.getNbrHeures(),i.getEnseignant()));
       }
        return lEetH;
    }

    public List<Infos> listeSallesetHeures(){
        List<Infos> lSetH = new ArrayList<>();
        if(info.isEmpty())
        {
            return null;
        }
        for(Infos i : info){
            lSetH.add(new Infos(i.getNbrHeures(),i.getSalle()));
        }
        return lSetH;
    }

    public List<Infos> listeCoursEtHeures(){
        List<Infos> lCetH = new ArrayList<>();
        if(info.isEmpty())
        {
            return null;
        }
        for(Infos i : info){
            lCetH.add(new Infos(i.getCours(),i.getNbrHeures()));
        }
        return lCetH;
    }

    public boolean salleCapaciteOk(Salle s){
        if(nbrEleves<=s.capacité){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean addCours(Cours c,int heures){
        boolean ok = info.contains(new Infos(c));
        if(!ok){
            Infos ajoutCours = new Infos(c,heures);
            info.add(ajoutCours);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean modifCours(Cours c,Enseignant e){
        boolean ok = info.contains(new Infos(c));
        if(ok){
            new Infos(c).setEnseignant(e);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean modifCours(Cours c,Salle s){
        boolean ok = info.contains(new Infos(c));
        if(ok){
            new Infos(c).setSalle(s);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean modifCours(Cours c,int heures){
        boolean ok = info.contains(new Infos(c));
        if(ok){
            new Infos(c).setNbrHeures(heures);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean suppCours(Cours c){
       boolean ok = info.contains(new Infos(c));
       if(ok){
           info.remove(new Infos(c));
           return true;
       }
       else{
           return false;
       }
    }
}



