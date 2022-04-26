package designpatterns.builder;

import ecole.metier.Infos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Classe {

    protected int idClasse;
    protected String sigle;
    protected int annee;
    protected String specialite;
    protected int nbrEleves;
    protected List<Infos> info = new ArrayList<>();

    private Classe(ClasseBuilder cb) {
        this.idClasse = cb.idClasse;
        this.sigle = cb.sigle;
        this.annee = cb.annee;
        this.specialite = cb.specialite;
        this.nbrEleves = cb.nbrEleves;
    }

    public int getIdClasse() {
        return idClasse;
    }

    public String getSigle() {
        return sigle;
    }

    public int getAnnee() {
        return annee;
    }

    public String getSpecialite() {
        return specialite;
    }

    public int getNbrEleves() {
        return nbrEleves;
    }

    @Override
    public String toString() {
        return "Classe{" +
                "idClasse=" + idClasse +
                ", sigle='" + sigle + '\'' +
                ", annee=" + annee +
                ", specialite='" + specialite + '\'' +
                ", nbrEleves=" + nbrEleves +
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

    public static class ClasseBuilder{
        protected int idClasse;
        protected String sigle;
        protected int annee;
        protected String specialite;
        protected int nbrEleves;

        public ClasseBuilder setIdClasse(int idClasse) {
            this.idClasse = idClasse;
            return this;
        }

        public ClasseBuilder setSigle(String sigle) {
            this.sigle = sigle;
            return this;
        }

        public ClasseBuilder setAnnee(int annee) {
            this.annee = annee;
            return this;
        }

        public ClasseBuilder setSpecialite(String specialite) {
            this.specialite = specialite;
            return this;
        }

        public ClasseBuilder setNbrEleves(int nbrEleves) {
            this.nbrEleves = nbrEleves;
            return this;
        }
        public Classe build() throws Exception{
            if(idClasse<=0 || sigle==null || annee<=0) throw new Exception("informations de construction incomplètes");
            return new Classe(this);
        }
    }
}



