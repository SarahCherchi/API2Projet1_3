package designpatterns.composite;

import java.util.Objects;

public abstract class Element {
    private int id;
    public Element(int id){
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public abstract int totNbrEleves();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return id == element.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

