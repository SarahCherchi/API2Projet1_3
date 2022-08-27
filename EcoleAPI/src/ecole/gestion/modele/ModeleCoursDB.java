package ecole.gestion.modele;

import ecole.metier.Cours;
import ecole.metier.Salle;
import myconnections.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ModeleCoursDB implements DAOCours {
    protected Connection dbConnect;

    public ModeleCoursDB() {
        dbConnect = DBConnection.getConnection();
    }

    @Override
    public Cours create(Cours cr) {

        String req1 = "insert into apicours(code,intitulé,idsalle) values(?,?,?)";
        String req2 = "select idcours from apicours where code=?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(req1); PreparedStatement pstm2 = dbConnect.prepareStatement(req2)) {
            pstm1.setString(1, cr.getCode());
            pstm1.setString(2, cr.getIntitule());
            pstm1.setInt(3, cr.getSalleParDefault().getIdSalle());
            int n = pstm1.executeUpdate();
            if (n == 0) {
                return null;
            }
            pstm2.setString(1, cr.getCode());
            ResultSet rs = pstm2.executeQuery();
            if (rs.next()) {
                int idcours = rs.getInt(1);
                cr.setIdCours(idcours);
                return cr;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Cours read(Cours cours) {
        String req = "select * from sallecours where CODE = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {
            pstm.setString(1, cours.getCode());
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int idcours = rs.getInt("IDCOURS");
                String intitule = rs.getString("INTITULÉ");

                int idsalle = rs.getInt("IDSALLE");
                String sigle = rs.getString("SIGLE");
                int capacite = rs.getInt("CAPACITÉ");

                Salle sl = new Salle(idsalle, sigle, capacite);
                Cours cr = new Cours(idcours, cours.getCode(), intitule, sl);
                return cr;

            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Cours update(Cours cr) {
        String req = "update apicours set code=?,intitulé=?,idsalle=? where idcours= ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {
            pstm.setInt(4, cr.getIdCours());
            pstm.setString(1, cr.getCode());
            pstm.setString(2, cr.getIntitule());
            pstm.setInt(3, cr.getSalleParDefault().getIdSalle());
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new Exception("aucun cours mis à jour");
            }
            return read(cr);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean delete(Cours cr) {

        String req = "delete from apicours where idcours= ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {

            pstm.setInt(1, cr.getIdCours());
            int n = pstm.executeUpdate();
            if (n == 0) return false;
            else return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Cours> readAll() {
        String req = "select * from apicours";
        List<Cours> lc = new ArrayList<>();
        try (PreparedStatement pstm = dbConnect.prepareStatement(req); ResultSet rs = pstm.executeQuery()) {
            while (rs.next()) {
                int idcours = rs.getInt("IDCOURS");
                String code = rs.getString("CODE");
                String intitule = rs.getString("INTITULÉ");

                lc.add(new Cours(idcours, code, intitule, null));
            }
            if (lc.isEmpty()) return null;
            return lc;

        } catch (Exception e) {
            return null;
        }
    }
}

