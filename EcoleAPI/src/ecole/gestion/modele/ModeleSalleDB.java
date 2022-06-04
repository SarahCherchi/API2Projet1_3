package ecole.gestion.modele;

import ecole.metier.Cours;
import ecole.metier.Salle;
import myconnections.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ModeleSalleDB implements DAOSalle{
    protected Connection dbConnect;

    public ModeleSalleDB() {
        dbConnect = DBConnection.getConnection();
    }

    @Override
    public Salle create(Salle sl) {

        String req1 = "insert into apisalle(sigle,capacité) values(?,?)";
        String req2 = "select idsalle from apisalle where sigle=?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(req1); PreparedStatement pstm2 = dbConnect.prepareStatement(req2)) {
            pstm1.setString(1, sl.getSigle());
            pstm1.setInt(2, sl.getCapacité());
            int n = pstm1.executeUpdate();
            pstm2.setString(1, sl.getSigle());
            ResultSet rs = pstm2.executeQuery();
            if (rs.next()) {
                int idsalle = rs.getInt(1);
                sl.setIdSalle(idsalle);
                return sl;
            } else {
               return null;
            }
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public Salle read(Salle salle) {
        String req = "select * from sallecours where SIGLE = ?";
        try ( PreparedStatement pstm = dbConnect.prepareStatement(req);) {
            pstm.setString(1, salle.getSigle());
            ResultSet rs = pstm.executeQuery() ;
            if (rs.next()) {
                int idsalle = rs.getInt("IDSALLE");
                int capacité = rs.getInt("CAPACITÉ");

                Salle sl = new Salle(idsalle, salle.getSigle(), capacité);
                List<Cours> lcr = new ArrayList<>();
                if(rs.getInt("IDCOURS")!=0){
                    do{
                        int idcours = rs.getInt("IDCOURS");
                        String code = rs.getString("CODE");
                        String intitulé = rs.getString("INTITULÉ");

                        Cours cr = new Cours(idcours,code,intitulé,salle);
                        lcr.add(cr);
                    }while(rs.next());

                }
                sl.setCours(lcr);
                return sl;

            } else {
                return null;
            }
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public Salle update(Salle sl) {
        String req = "update apisalle set sigle=?,capacité=? where idsalle= ?";
        try ( PreparedStatement pstm = dbConnect.prepareStatement(req)) {

            pstm.setInt(3, sl.getIdSalle());
            pstm.setString(1, sl.getSigle());
            pstm.setInt(2, sl.getCapacité());
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new Exception("aucune salle mise à jour");
            }
            return read(sl);
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean delete(Salle sl) {

        String req = "delete from apisalle where idsalle= ?";
        try ( PreparedStatement pstm = dbConnect.prepareStatement(req)) {

            pstm.setInt(1, sl.getIdSalle());
            int n = pstm.executeUpdate();
            if (n == 0) return false;
            else return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public List<Salle> readAll() {
        String req = "select * from apisalle";
        List<Salle> ls = new ArrayList<>();
        try ( PreparedStatement pstm = dbConnect.prepareStatement(req);  ResultSet rs = pstm.executeQuery()) {
            while (rs.next()) {
                int idsalle = rs.getInt("IDSALLE");
                String sigle = rs.getString("SIGLE");
                int capacité = rs.getInt("CAPACITÉ");

                ls.add(new Salle(idsalle, sigle, capacité));
            }
            if(ls.isEmpty()) return null;
            return ls;

        }
        catch (Exception e){
            return null;
        }
    }

}
