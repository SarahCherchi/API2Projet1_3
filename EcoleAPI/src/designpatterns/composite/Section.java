package designpatterns.composite;

import java.util.HashSet;
import java.util.Set;

public class Section extends Element {
    private String nom;
    private Set<Element> elts=new HashSet<>();

    public Section(int id,String nom){
        super(id);
        this.nom=nom;
    }

    @Override
    public String toString() {
        StringBuilder aff= new StringBuilder(getId()+" "+nom+"\n");

        for(Element e:elts){
            aff.append(e+"\n");
        }
        return aff+"nombre total d'élèves " +nom +" = "+totNbrEleves()+"\n";
    }



    @Override
    public int totNbrEleves() {
        int somme=0;
        for(Element pc:elts){
            somme+=pc.totNbrEleves();
        }
        return somme;
    }

    public Set<Element> getElts() {
        return elts;
    }
}
