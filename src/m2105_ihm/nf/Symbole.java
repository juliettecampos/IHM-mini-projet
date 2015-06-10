/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.nf;

/**
 *
 * @author juliette campos

 */
public enum Symbole {
    FLEUR("Fleur"),
    ANIMAL("Animal"),
    MONTAGNE("Montagne"),
    ARBRE("Arbre"),
    VOITURE("Voiture");
    
    private final String label;
    
    private Symbole(String label) { 
        this.label = label; 
    }
    
    public String toString() { return this.label; }    
}
