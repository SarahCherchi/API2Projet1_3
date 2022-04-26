package dbdemo;

import myconnections.DBConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Scanner;

public class GestEnseignant {

    private Scanner sc = new Scanner(System.in);
    private Connection dbConnect;

    public void gestion() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        System.out.println("connexion établie");
        do {
            System.out.println("1.ajout\n2.recherche\n3.modification\n4.suppression\n5.fin");
            System.out.println("choix : ");
            int ch = sc.nextInt();
            sc.skip("\n");
            switch (ch) {
                case 1:
                    ajout();
                    break;
                case 2:
                    recherche();
                    break;
                case 3:
                    modification();
                    break;
                case 4:
                    suppression();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("choix invalide recommencez ");
            }
        } while (true);

    }

    public void ajout() {

        System.out.println("matricule :");
        Integer matricule = sc.nextInt();
        sc.skip("\n");
        System.out.print("nom :");
        String nom = sc.nextLine();
        System.out.print("prénom :");
        String prenom = sc.nextLine();
        System.out.print("tel :");
        String tel = sc.nextLine();
        System.out.println("charge horaire hebdomadaire :");
        Integer chargeSem = sc.nextInt();
        sc.skip("\n");
        System.out.println("salaire mensuel  :");
        BigDecimal salaireMensu = sc.nextBigDecimal();
        sc.skip("\n");
        System.out.print("date d'engagement :");
        Date dateEngag = readDate();
        String query1 = "insert into APIENSEIGNANT(matricule,nom,prenom,tel,chargeSem,salaireMensu,dateEngag) values(?,?,?,?,?,?,?)";
        String query2 = "select idenseignant from APIENSEIGNANT where nom= ? and prenom =? and tel =?";
        try(PreparedStatement pstm1= dbConnect.prepareStatement(query1);
            PreparedStatement pstm2= dbConnect.prepareStatement(query2);
        ){
            pstm1.setInt(1,matricule);
            pstm1.setString(2,nom);
            pstm1.setString(3,prenom);
            pstm1.setString(4,tel);
            pstm1.setInt(5,chargeSem);
            pstm1.setBigDecimal(6,salaireMensu);
            pstm1.setDate(7,dateEngag);
            int n = pstm1.executeUpdate();
            System.out.println(n+" ligne insérée");
            if(n==1){
                pstm2.setString(1,nom);
                pstm2.setString(2,prenom);
                pstm2.setString(3,tel);
                ResultSet rs= pstm2.executeQuery();
                if(rs.next()){
                    int idenseignant= rs.getInt(1);
                    System.out.println("idenseignant = "+idenseignant);
                }
                else System.out.println("record introuvable");
                rs.close();
            }

        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }
    }

    private Date readDate() {

        return null;
    }


    public void recherche() {

        System.out.println("id de l'enseignant recherché ");
        int idrech = sc.nextInt();
        String query = "select * from APIENSEIGNANT where idenseignant = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,idrech);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                int matricule = rs.getInt(2);
                String nom = rs.getString(3);
                String prenom = rs.getString(4);
                String tel = rs.getString(5);
                int chargeSem = rs.getInt(6);
                BigDecimal salaireMensu= rs.getBigDecimal(7);
                Date dateEngag = rs.getDate(8);
                System.out.printf("%d %d %s %s %s %d %f %t\n",idrech,matricule,nom,prenom,tel,chargeSem,salaireMensu,dateEngag);
            }
            else System.out.println("record introuvable");
            rs.close();
        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }

    }

    public void modification() {
        System.out.println("id de l'enseignant recherché ");
        int idrech = sc.nextInt();
        sc.skip("\n");
        System.out.println("nouveau téléphone ");
        String ntel = sc.nextLine();
        String query = "update APIENSEIGNANT set tel=? where idenseignant = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1,ntel);
            pstm.setInt(2,idrech);
            int n = pstm.executeUpdate();
            if(n!=0) System.out.println(n+ "ligne mise à jour");
            else System.out.println("record introuvable");

        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
        }
    }
    public void suppression() {
        System.out.println("id de l'enseignant recherché ");
        int idrech = sc.nextInt();
        String query = "delete from APIENSEIGNANT where idenseignant = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,idrech);
            int n = pstm.executeUpdate();
            if(n!=0) System.out.println(n+ "ligne supprimée");
            else System.out.println("record introuvable");

        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }

    }



    public static void main(String[] args) {

        GestEnseignant g = new GestEnseignant();
        g.gestion();
    }

}
