package designpatterns.composite;

public class DemoComposite {
    public static void main(String[] args) {
        Classe c1 = new Classe(1, "C00001", 1, "spé1", 10);
        Classe c2 = new Classe(2, "C00002", 2, "spé2", 25);
        Classe c3 = new Classe(3, "C00003", 3, "spé3", 12);
        Classe c4 = new Classe(4, "C00004", 4, "spé4", 15);
        Section s1 = new Section(5, "générale");
        Section s2 = new Section(6, "sec1");
        Section s3 = new Section(7, "sec2");
        s1.getElts().add(c1);
        s1.getElts().add(s2);
        s1.getElts().add(s3);
        s2.getElts().add(c2);
        s2.getElts().add(c3);
        s3.getElts().add(c4);
        System.out.println(s1);

    }
}
