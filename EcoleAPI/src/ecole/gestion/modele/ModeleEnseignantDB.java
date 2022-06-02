package ecole.gestion.modele;

import ecole.metier.Enseignant;
import myconnections.DBConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModeleEnseignantDB implements DAOEnseignant {
    protected Connection dbConnect;

    public ModeleEnseignantDB() {
        dbConnect = DBConnection.getConnection();
    }

    @Override
    public Enseignant create(Enseignant ens) {

        String req1 = "insert into apienseignant(matricule,nom,prenom,tel,chargesem,salairemensu,dateengag) values(?,?,?,?,?,?,?)";
        String req2 = "select idenseignant from apienseignant where nom=? and prenom=? and tel=?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(req1); PreparedStatement pstm2 = dbConnect.prepareStatement(req2)) {
            pstm1.setString(1, ens.getMatricule());
            pstm1.setString(2, ens.getNom());
            pstm1.setString(3, ens.getPrenom());
            pstm1.setString(4, ens.getTel());
            pstm1.setInt(5, ens.getChargeSem());
            pstm1.setBigDecimal(6, ens.getSalaireMensu());
            pstm1.setDate(7, java.sql.Date.valueOf(String.valueOf(ens.getDateEngag())));
            int n = pstm1.executeUpdate();
            if (n == 0) {
                return null;
            }
            pstm2.setString(1, ens.getNom());
            pstm2.setString(2, ens.getPrenom());
            pstm2.setString(3, ens.getTel());
            ResultSet rs = pstm2.executeQuery();
            if (rs.next()) {
                int idEnseignant = rs.getInt(1);
                ens.setIdEnseignant(idEnseignant);
                return ens;
            } else {
                throw new Exception("aucun enseignant trouvé");
            }
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public Enseignant read(Enseignant enseignant) {
        String req = "select * from apienseignant where idenseignant = ?";
        try ( PreparedStatement pstm = dbConnect.prepareStatement(req);) {
            pstm.setInt(1, enseignant.getIdEnseignant());
            ResultSet rs = pstm.executeQuery() ;
            if (rs.next()) {
                String matricule = rs.getString("MATRICULE");
                String nom = rs.getString("NOM");
                String prenom = rs.getString("PRENOM");
                String tel = rs.getString("TEL");
                int chargeSem = rs.getInt("CHARGESEM");
                BigDecimal salaireMensu = rs.getBigDecimal("SALAIREMENSU");
                Date dateengag = rs.getDate("DATEENGAG");

                Enseignant ens = new Enseignant(enseignant.getIdEnseignant(),matricule, nom, prenom, tel,chargeSem,salaireMensu,dateengag);
                return ens;

            } else {
                return null;
            }
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public Enseignant update(Enseignant ens) {
        String req = "update apienseignant set matricule=?,nom=?,prenom=?,tel=?,chargesem=?,salairemensu=?,dateengag=? where idenseignant= ?";
        try ( PreparedStatement pstm = dbConnect.prepareStatement(req)) {

            pstm.setInt(8, ens.getIdEnseignant());
            pstm.setString(1, ens.getMatricule());
            pstm.setString(2, ens.getNom());
            pstm.setString(3, ens.getPrenom());
            pstm.setString(4, ens.getTel());
            pstm.setInt(5, ens.getChargeSem());
            pstm.setBigDecimal(6, ens.getSalaireMensu());
            pstm.setDate(7, java.sql.Date.valueOf(String.valueOf(ens.getDateEngag())));

            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new Exception("aucun enseignant mis à jour");
            }
            return read(ens);
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean delete(Enseignant ens) {

        String req = "delete from apienseignant where idenseignant= ?";
        try ( PreparedStatement pstm = dbConnect.prepareStatement(req)) {

            pstm.setInt(1, ens.getIdEnseignant());
            int n = pstm.executeUpdate();
            if (n == 0) return false;
            else return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public List<Enseignant> readAll() {
        String req = "select * from apienseignant";
        List<Enseignant> le = new ArrayList<>();
        try ( PreparedStatement pstm = dbConnect.prepareStatement(req);  ResultSet rs = pstm.executeQuery()) {
            while (rs.next()) {
                int idenseignant = rs.getInt("IDENSEIGNANT");
                String matricule = rs.getString("MATRICULE");
                String nom = rs.getString("NOM");
                String prenom = rs.getString("PRENOM");
                String tel = rs.getString("TEL");
                int chargeSem = rs.getInt("CHARGESEM");
                BigDecimal salaireMensu = rs.getBigDecimal("SALAIREMENSU");
                Date dateengag = rs.getDate("DATEENGAG");
                le.add(new Enseignant(idenseignant,matricule, nom, prenom,tel,chargeSem,salaireMensu,dateengag));
            }
            if(le.isEmpty()) return null;
            return le;

        }
        catch (Exception e){
            return null;
        }
    }
}
