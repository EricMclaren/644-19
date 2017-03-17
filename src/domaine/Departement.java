package domaine;

/** 
 * Entité représentant un Departement
 *
 * @author Peter DAEHNE - HEG Genève
  * @author Eric Mclaren - HEG Genève
 * @version 0.1
 */
public class Departement {
    private int noDept;
    private String nomDept;
    private Lieu lieu;
    /* Constructeur */
    public Departement (int noDept, String nomDept, Lieu lieu) {
        this.noDept = noDept;
        this.nomDept = nomDept;
        this.lieu = lieu;
    } // Constructeur

    //GETTERS
    public int getNo(){
        return this.noDept;
    }    
    public String getNom(){
        return nomDept;
    }    
    public Lieu getLieu(){
        return lieu;
    }

    public String toString(){
        return this.noDept + " - " + this.nomDept + " - " + this.lieu;
    }
} // Departement