package base;

import domaine.Departement;
import domaine.Lieu;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Gestion des accès à la base de données pour l'entité Departement.
 *
 * @author Peter DAEHNE - HEG-Genève
 * @author Eric Mclaren - HEG Genève
 * @version Version 0.1
*/
public class DepartementDao {
  
    /** Retourne le Departement d'identifiant noDept, null en cas d'erreur */
    public static Departement getDepartement (int noDept) throws SQLException {
        Departement resultat = null;
        Connection con = ConnexionBase.get();
        Statement rqst = con.createStatement();
        ResultSet rs = rqst.executeQuery("SELECT NomDept, departement.NoLieu, NomLieu FROM departement,lieu WHERE NoDept = "+noDept+" AND departement.noLieu = lieu.noLieu");
        while(rs.next()){
            Lieu lieu = new Lieu(rs.getInt("NoLieu"), rs.getString("NomLieu"));
            resultat = new Departement(noDept, rs.getString("NomDept"), lieu);
        }        
        rqst.close();
        return resultat;
    } // getDepartement
    
    public static ArrayList getDepartements(){
        ArrayList resultat = new ArrayList();
        try{
            Connection con = ConnexionBase.get();
            Statement rqst = con.createStatement();
            ResultSet rs = rqst.executeQuery("SELECT * FROM departement");
            while(rs.next()){
                resultat.add(rs.getString("NomDept"));
            }        
            rqst.close();
        }catch(SQLException e){
            
        }
        System.out.println(resultat);
        return resultat;
    }
    
} // DepartementDao
