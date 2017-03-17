package domaine;

/** 
 * Entité représentant une Fonction
 *
 * @author Peter DAEHNE - HEG Genève
  * @author Eric Mclaren - HEG Genève
 * @version 0.1
 */
public class Fonction {
    private int noFonc;
    private String nomFonc;
    /** Constructeur */    
    public Fonction (int noFonc, String nomFonc) {
        this.noFonc = noFonc;
        this.nomFonc = nomFonc;
    } // Constructeur
    
    public String getNom(){
        return this.nomFonc;
    }
} // Fonction