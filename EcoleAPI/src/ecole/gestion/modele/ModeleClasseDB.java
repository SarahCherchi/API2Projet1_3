package ecole.gestion.modele;

import ecole.metier.*;
import myconnections.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ModeleClasseDB implements DAOClasse {

    protected Connection dbConnect;

    public ModeleClasseDB() {
        dbConnect = DBConnection.getConnection();
    }

    @Override
    public Classe create(Classe cl) {

        String req1 = "insert into apiclasse(sigle,année,spécialité,nbreleves) values(?,?,?,?)";
        String req2 = "select idclasse from apiclasse where sigle=?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(req1); PreparedStatement pstm2 = dbConnect.prepareStatement(req2)) {
            pstm1.setString(1, cl.getSigle());
            pstm1.setInt(2, cl.getAnnee());
            pstm1.setString(3, cl.getSpecialite());
            pstm1.setInt(4, cl.getNbrEleves());
            int n = pstm1.executeUpdate();
            pstm2.setString(1, cl.getSigle());
            ResultSet rs = pstm2.executeQuery();
            if (rs.next()) {
                int idclasse = rs.getInt(1);
                cl.setIdClasse(idclasse);
                return cl;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Classe read(Classe classe) {
        String 	 req = "select * from vueapi2 where sigle = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req);) {
            pstm.setString(1,classe.getSigle());
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int idcl = rs.getInt("IDCLASSE");
                int annee = rs.getInt("ANNÉE");
                String specialite = rs.getString("SPÉCIALITÉ");
                int nbrEleves = rs.getInt("NBRELEVES");
                int idcours = rs.getInt("IDCOURS");
                Classe cl = new Classe(idcl, classe.getSigle(), annee, specialite, nbrEleves);

                List<Infos> linf = new ArrayList<>();
                if (idcours != 0) {
                    do {

                        int idsalle = rs.getInt("IDSALLE");
                        int cap = rs.getInt("CAPACITÉ");

                        idcours = rs.getInt("IDCOURS");
                        String code = rs.getString("CODE");
                        String intitule = rs.getString("INTITULÉ");

                        int idens = rs.getInt("IDENSEIGNANT");

                        Salle sl = new Salle(idsalle,cap);
                        Cours cr = new Cours(idcours,code, intitule,sl);

                        if (idens != 0) {
                            do {

                                int nbrheures = rs.getInt("NBRHEURES");

                                idens = rs.getInt("IDENSEIGNANT");
                                String nom = rs.getString("NOM");

                                Enseignant ens = new Enseignant(idens,nom);
                                Infos inf = new Infos(cr,nbrheures,sl, ens);
                                linf.add(inf);
                            } while (rs.next());

                        }

                    } while (rs.next());

                }
                cl.setInfo(linf);
                return cl;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    /*@Override
    public Classe read(Classe classe) {
        String 	 req = "select * from view1 where sigle = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req);) {
            pstm.setString(1,classe.getSigle());
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int idc = rs.getInt("IDCLASSE");
                int annee = rs.getInt("ANNÉE");
                String specialite = rs.getString("SPÉCIALITÉ");
                int nbrEleves = rs.getInt("NBRELEVES");
                int idens = rs.getInt("IDENSEIGNANT");
                Classe cl = new Classe(idc, classe.getSigle(), annee, specialite, nbrEleves);

                List<Infos> linf = new ArrayList<>();
                if (idens != 0) {
                    do {
                        String code = rs.getString("CODE");
                        String intitule = rs.getString("INTITULÉ");

                        int idsalle = rs.getInt("IDSALLE");
                        int cap = rs.getInt("CAPACITÉ");

                        int nbrheures = rs.getInt("NBRHEURES");

                        idens = rs.getInt("IDENSEIGNANT");
                        String nom = rs.getString("NOM");

                        Cours cr = new Cours(code, intitule);
                        Salle sl = new Salle(idsalle,cap);
                        Enseignant ens = new Enseignant(idens,nom);
                        Infos inf = new Infos(cr,nbrheures,sl, ens);
                        linf.add(inf);
                    } while (rs.next());

                }
                cl.setInfo(linf);
                return cl;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    } */

    @Override
    public Classe update(Classe cl) {
        String req = "update apiclasse set nbreleves= ? where sigle = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {

            pstm.setString(2, cl.getSigle());
            pstm.setInt(1,cl.getNbrEleves());

            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new Exception("Mise à jour non effectuée");

               // return null;
            }

            return read(cl);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean delete(Classe cl) {

        String req = "delete from apiclasse where idclasse= ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {

            pstm.setInt(1, cl.getIdClasse());
            int n = pstm.executeUpdate();
            if (n == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Classe> readAll() {
        String req = "select * from apiclasse";
        List<Classe> lcl = new ArrayList<>();
        try (PreparedStatement pstm = dbConnect.prepareStatement(req);) {

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int idclasse = rs.getInt("IDCLASSE");
                String sigle = rs.getString("SIGLE");
                int annee = rs.getInt("ANNÉE");
                String specialite = rs.getString("SPÉCIALITÉ");
                int nbreleves = rs.getInt("NBRELEVES");
                Classe cl = new Classe(idclasse, sigle, annee, specialite, nbreleves);
                lcl.add(cl);
            }
            if (lcl.isEmpty()) return null;
            return lcl;

        } catch (Exception e) {
            return null;
        }
    }

  @Override
    public boolean addCours(Classe cl,Cours c, int heures) {
        String req = "insert into apiinfos(idcours,idclasse,nbrheures,idsalle) values(?,?,?,?)";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req))
              {
                  pstm.setInt(1, c.getIdCours());
                  pstm.setInt(2, cl.getIdClasse());
                  pstm.setInt(3, heures);
                  pstm.setInt(4, c.getSalleParDefault().getIdSalle());
            pstm.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean modifCoursE(Classe cl, Cours c, Enseignant ens) {
        String req = "update apiinfos set idenseignant=? where idclasse=? and idcours=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req))
        {
            pstm.setInt(1, ens.getIdEnseignant());
            pstm.setInt(2, cl.getIdClasse());
            pstm.setInt(3, c.getIdCours());
            pstm.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean modifCoursS(Classe cl, Cours c, Salle s) {
        String req = "update apiinfos set idsalle=? where idclasse=? and idcours=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req))
        {
            pstm.setInt(1, s.getIdSalle());
            pstm.setInt(2, cl.getIdClasse());
            pstm.setInt(3, c.getIdCours());
            pstm.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean modifCoursH(Classe cl, Cours c, int heures) {
        String req = "update apiinfos set nbrheures=? where idclasse=? and idcours=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req))
        {
            pstm.setInt(1, heures);
            pstm.setInt(2, cl.getIdClasse());
            pstm.setInt(3, c.getIdCours());
            pstm.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean suppCours(Classe cl, Cours c) {
        String req = "delete from apiinfos where idclasse=? and idcours=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req))
        {
            pstm.setInt(1, cl.getIdClasse());
            pstm.setInt(2, c.getIdCours());
            pstm.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int nbrHeuresTot(Classe cl) {
        String req = "select * from heuretot where idclasse = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req);) {
            pstm.setInt(1, cl.getIdClasse());
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                int nbrheureTot = rs.getInt("NBRHEURES");
                return nbrheureTot;
            }
            else{
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
    }

}