package ecole.gestion.modele;

import ecole.metier.*;

public interface DAOClasse extends DAO<Classe>{
    boolean addCours(Classe cl, Cours c, int heures);
    boolean modifCoursE(Classe cl, Cours c, Enseignant ens);
    boolean modifCoursS(Classe cl, Cours c, Salle s);
    boolean modifCoursH(Classe cl, Cours c, int heures);
    boolean suppCours(Classe cl, Cours c);
    boolean salleCapaciteOk(Classe cl, Salle s);
    int nbrHeuresTot(Classe cl);
}
