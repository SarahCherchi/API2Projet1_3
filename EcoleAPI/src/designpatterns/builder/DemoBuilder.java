package designpatterns.builder;

public class DemoBuilder {
    public static void main(String[] args)  {

        try {
            Classe cl1 = new Classe.ClasseBuilder().
                    setIdClasse(1).
                    setSigle("2IA").
                    setAnnee(2).
                    setSpecialite("Informatique").
                    setNbrEleves(17).
                    build();
            System.out.println(cl1);
        } catch (Exception e) {
            System.out.println("erreur "+e);
        }

        try {
            Classe cl2 = new Classe.ClasseBuilder().
                    setIdClasse(1).
                    setAnnee(2).
                    setSpecialite("Informatique").
                    setNbrEleves(17).
                    build();
            System.out.println(cl2);
        } catch (Exception e) {
            System.out.println("erreur "+e);
        }

    }
}
