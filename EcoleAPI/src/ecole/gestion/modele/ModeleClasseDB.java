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
        String req = "select * from classesallecoursens where idclasse = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req);) {
            pstm.setInt(1, classe.getIdClasse());
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                String sigle = rs.getString("SIGLE");
                int annee = rs.getInt("ANNÉE");
                String specialite = rs.getString("SPECIALITÉ ");
                int nbrEleves = rs.getInt("NBRELEVES");
                Classe cl = new Classe(classe.getIdClasse(), sigle, annee, specialite, nbrEleves);
                int idens = rs.getInt("IDENSEIGNANT");

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
    }

    @Override
    public Classe update(Classe cl) {
        String req = "update apiclasse set nbreleves= ? where idclasse = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {

            pstm.setInt(5, cl.getIdClasse());
            pstm.setString(1, cl.getSigle());
            pstm.setInt(2, cl.getAnnee());
            pstm.setString(3, cl.getSpecialite());
            pstm.setInt(4, cl.getNbrEleves());
            int n = pstm.executeUpdate();
            if (n == 0) {
                return null;
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
}