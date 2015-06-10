/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.nf;

/**
 
 * @author juliette campos
 */
public enum Hobby implements java.io.Serializable {
    CINEMA("Cin\u00E9ma"),
    LECTURE("Lecture"),
    MUSIQUE("Musique"),
    SPORT("Sport"),
    MANGER("Manger");
    
    
    private final String label;
    
    private Hobby(String label) { 
        this.label = label; 
    }
    
    public String toString() { return this.label; }     
}