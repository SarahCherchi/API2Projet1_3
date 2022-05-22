package designpatterns.observer;

import java.math.BigDecimal;
import java.time.LocalDate;

public class demoObserver {
    public static void main(String[] args) {
        Classe c1 = new Classe(1, "C00001", 2, "informatique", 20);
        Classe c2 = new Classe(2, "C00002", 3, "informatique", 15);
        Enseignant ens1= new Enseignant(3,"E00003","Durand","Jean","0456/990088", BigDecimal.valueOf(500), LocalDate.of(2002,11,5));
        Enseignant ens2 = new Enseignant(4,"E00004","Dupond","Anne","0451/441122", BigDecimal.valueOf(500), LocalDate.of(2012,7,21));
        c1.addObserver(ens1);
        c1.addObserver(ens2);
        c2.addObserver(ens1);

        c1.setNbrEleves(21);
        c2.setNbrEleves(12);
    }
}
