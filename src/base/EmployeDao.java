package base;

import domaine.Departement;
import domaine.Employe;
import domaine.Fonction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 * Gestion des accès à la base de données pour l'entité Employe.
 *
 * @author Peter DAEHNE - HEG-Genève
 * @version Version 1.0
*/
public class EmployeDao {
  
    /** Retourne la liste complète des Employes dans l'ordre des nom et prénom, null en cas d'erreur */
    public static ArrayList getEmployes () {
        ArrayList liste = new ArrayList();
        try {
            Connection con = ConnexionBase.get();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT NoEmpl, NomEmpl, PrenomEmpl, NoFonc, DateEmpl, NoDept FROM Employe ORDER BY NomEmpl, PrenomEmpl");
            while (rs.next()) {
                Fonction f = FonctionDao.getFonction(rs.getInt("NoFonc"));
                Departement d = DepartementDao.getDepartement(rs.getInt("NoDept"));
                Employe e = new Employe(rs.getInt("NoEmpl"), rs.getString("NomEmpl"), rs.getString("PrenomEmpl"), f, rs.getDate("DateEmpl"), d);
                liste.add(e);
            }
            stmt.close();
        }
        catch (SQLException e) {System.out.println("EmployeDao.getEmployes(): " + e.getMessage()); e.printStackTrace(); return null;}
        return liste;
    } // getEmployes
    
    public static Employe getEmploye(String name) throws SQLException{
        Employe empl=null;
        String[] parts = name.split(" ");
        String nomEmpl=parts[0];
        String prenomEmpl = parts[1];
        Connection con = ConnexionBase.get();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT NoEmpl, NomEmpl, PrenomEmpl, NoFonc, DateEmpl, NoDept FROM Employe WHERE NomEmpl LIKE '" + nomEmpl + "' AND PrenomEmpl LIKE '" + prenomEmpl+"'");
        while (rs.next()) {
            Fonction f = FonctionDao.getFonction(rs.getInt("NoFonc"));
            Departement d = DepartementDao.getDepartement(rs.getInt("NoDept"));
            empl = new Employe(rs.getInt("NoEmpl"), rs.getString("NomEmpl"), rs.getString("PrenomEmpl"), f, rs.getDate("DateEmpl"), d);
        }
        stmt.close();
        return empl;
    }
    
    public static Boolean delEmploye(String name){
        Boolean deleted = false;
        try{
            String[] parts = name.split(" ");
            String nomEmpl=parts[0];
            String prenomEmpl = parts[1];
            Connection con = ConnexionBase.get();
            Statement stmt = con.createStatement();
            int nbDel = stmt.executeUpdate("DELETE FROM `employe` WHERE `NomEmpl` LIKE '"+nomEmpl+"'AND `PrenomEmpl` LIKE '"+prenomEmpl+"'" );
            stmt.close();
            if(nbDel > 0){
                deleted = true;
            }
        }catch(SQLException e){
            System.err.println("ERROR : " + e.getErrorCode());
            deleted = false;
        }
        return deleted;
    }
    
    public static void saveEmploye(String nom, String prenom, Date dateEng, int foncId, int depId){
        try{
            Connection con = ConnexionBase.get();
            PreparedStatement stmt = con.prepareStatement("INSERT INTO employe (NomEmpl, PrenomEmpl, NoFonc, DateEmpl, NoChef, NoDept) VALUES(?, ?, ?, ?, ?, ?)" );
            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            stmt.setInt(3, foncId+1);
            long date = dateEng.getTime();
            stmt.setDate(4, new java.sql.Date(date));
            stmt.setInt(5, 1);
            stmt.setInt(6, depId+1);
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
    
} // EmployeDao
