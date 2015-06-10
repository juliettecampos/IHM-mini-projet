/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import m2105_ihm.Controleur;
import m2105_ihm.nf.Mois;
import m2105_ihm.nf.Contact;
import m2105_ihm.nf.Evenement;

/**
 *
 * @author IUT2
 */
public class FicheEvtUI extends javax.swing.JPanel {
    
    /*
     * Attributs
     */
    private PlanningUI planning;
    private Controleur controleur;
    private JButton  valider;

    /**
     * Creates new form CarnetUI
     */
    public FicheEvtUI(PlanningUI planning) {
        super();
        
        this.planning = planning;
        initUIComponents();
        initListeners();
    }

    /**
     * Initialise la gestion des événements
     */
    private void initListeners() {
        
        /** Projet : à compléter **/
        
    }
    
    /**
     * Crée et positionne les composants graphiques constituant l'interface
     */    
    private void initUIComponents() {
        
        /** Projet : à compléter **/  
        
      
      ArrayList<Evenement> e  = new ArrayList<Evenement>();
      this.add(new javax.swing.JLabel("Evenements"));  
      e = planning.controleur.nf.getEvenements();
      
      JPanel evenement=new JPanel();
      this.add(evenement);
      JPanel boutons = new JPanel();
      
      
       valider = new javax.swing.JButton("VALIDER");
       boutons.add(valider);
       this.add(boutons);
      
 
      
      
      
    }

    /**
     * Affecte des valeurs au formulaire pour un événement
     * @param Evenement un événement
     * @return
     */        
    public boolean setValues(Evenement event) {
        if (event == null) { return false; }            
                  
        /** Projet : à compléter **/
            
        return false;
    }

    /**
     * Retourne les valeurs associées au formulaire de fiche événement
     * @param Evenement événement à affecter
     * @return 
     */    
    public boolean getValues(Evenement event) {
        
        if (event == null) { return false; }
        
        /** Projet : à compléter **/
        
        return true;
    }
}
