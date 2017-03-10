package base;

import domaine.Departement;
import domaine.Fonction;
import domaine.Lieu;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Gestion des accès à la base de données pour l'entité Fonction.
 *
 * @author Peter DAEHNE - HEG-Genève
 * @version Version 0.1
*/
public class FonctionDao {
  
    /** Retourne la Fonction d'identifiant noFonc, null en cas d'erreur */
    public static Fonction getFonction (int noFonc) {
        Fonction resultat = null;
        try{
            Connection con = ConnexionBase.get();
            Statement rqst = con.createStatement();
            ResultSet rs = rqst.executeQuery("SELECT NomFonc FROM fonction WHERE NoFonc = "+noFonc);
            while(rs.next()){
                resultat = new Fonction(noFonc, rs.getString("NomFonc"));
            }        
            rqst.close();
        }catch(SQLException e){
            
        }
        return resultat;
    } // getFonction
    
    public static ArrayList getFonctions(){
        ArrayList resultat = new ArrayList();
        try{
            Connection con = ConnexionBase.get();
            Statement rqst = con.createStatement();
            ResultSet rs = rqst.executeQuery("SELECT * FROM fonction");
            while(rs.next()){
                resultat.add(rs.getString("NomFonc"));
            }        
            rqst.close();
        }catch(SQLException e){
            
        }
        return resultat;
    }
  
} // FonctionDao
