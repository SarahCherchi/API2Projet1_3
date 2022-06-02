
package ecole.gestion.modele;

import java.util.List;

public interface DAO<T> {

    T create(T newObj);

    boolean delete(T ObjRech);

    T read(T objRech);

    T update(T objRech);

    List<T> readAll();
}
