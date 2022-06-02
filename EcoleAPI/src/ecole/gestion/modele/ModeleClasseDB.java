package ecole.gestion.modele;

import ecole.metier.Classe;
import ecole.metier.Infos;
import myconnections.DBConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ModeleClasseDB implements DAOClasse {
    @Override
    public Classe create(Classe newObj) {
        return null;
    }

    @Override
    public boolean delete(Classe ObjRech) {
        return false;
    }

    @Override
    public Classe read(Classe objRech) {
        return null;
    }

    @Override
    public Classe update(Classe objRech) {
        return null;
    }

    @Override
    public List<Classe> readAll() {
        return null;
    }
   /* protected Connection dbConnect;

    public ModeleClasseDB() {
        dbConnect = DBConnection.getConnection();
    }

    @Override
    public Classe create(Classe cl) {

        String req1 = "insert into apiclasse(sigle,année,spécilité,nbreleves) values(?,?,?,?)";
        String req2 = "select idclasse from apiclasse where sigle=?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(req1); PreparedStatement pstm2 = dbConnect.prepareStatement(req2)) {
            pstm1.setString(1, cl.getSigle());
            pstm1.setInt(2, cl.getAnnee());
            pstm1.setString(3, cl.getSpecialite());
            pstm1.setInt(4, cl.getNbrEleves());
            int n = pstm1.executeUpdate();
            if (n == 0) {
                return null;
            }
            pstm2.setString(1, cl.getSigle());
            ResultSet rs = pstm2.executeQuery();
            if (rs.next()) {
                int idclasse = rs.getInt(1);
                cl.setIdClasse(idclasse);
                return cl;
            } else {
                throw new Exception("aucune classe trouvée");
            }
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public Classe read(Classe classe) {
        String req = "select * from apiinfos where idclasse = ?";
        try ( PreparedStatement pstm = dbConnect.prepareStatement(req);) {
            pstm.setInt(1, classe.getIdClasse());
            ResultSet rs = pstm.executeQuery() ;
            if (rs.next()) {
                String sigle = rs.getString("SIGLE");
                int annee = rs.getInt("ANNEE");
                String specialite = rs.getString("SPECIALITE");
                int nbrEleves = rs.getInt("NOMBRE ELEVES");
                Classe cl = new Classe(classe.getIdClasse(), sigle, annee, specialite,nbrEleves);
                int idcl= rs.getInt("IDCLASSE");

                List<Infos> linf = new ArrayList<>();
                if (idcl != 0) {
                    do {
                        int idcours = rs.getInt("IDCOURS");
                        idcl = rs.getInt("IDCLASSE");
                        int nbrheures = rs.getInt("NOMBRE HEURES");
                        int idsalle = rs.getInt("IDSALLE");
                        int idenseignant = rs.getInt("IDENSEIGNANT");

                        Classe pr = new Produit(idpr, numprod, description);
                        Ligne lg = new Ligne(pr, quant, pa);
                        linf.add(lg);
                    } while(rs.next());

                }
                cf.setLignes(llg);
                return cf;
            } else {
                return null;
            }
        }
        catch (Exception e){
            return null;
        }

        A continuer avec une vue trop dur sans

*/
}