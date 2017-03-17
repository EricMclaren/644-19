package domaine;

/** 
 * Entité représentant un Lieu
 *
 * @author Peter DAEHNE - HEG Genève
  * @author Eric Mclaren - HEG Genève
 * @version 0.1
 */
public class Lieu {
    private int noLieu;
    private String nomLieu;
    /** Constructeur */
    public Lieu (int noLieu, String nomLieu) {
        this.noLieu = noLieu;
        this.nomLieu = nomLieu;
    } // Constructeur
    
    //GETTERS
    public int getNo(){
        return noLieu;
    }
    public String getNom(){
        return nomLieu;
    }
    
    public String toString(){
        return "[ " + noLieu + " - " + nomLieu + " ]";
    }
} // Lieu