/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import m2105_ihm.nf.Mois;
import m2105_ihm.nf.Contact;
import m2105_ihm.nf.Evenement;
import m2105_ihm.nf.GroupeContacts;
import m2105_ihm.nf.NoyauFonctionnel;

/**
 *
 * @author IUT2
 */
public class FicheEvtUI extends javax.swing.JPanel {
    
    /*
     * Attributs
     */
    private PlanningUI          planning;
    private NoyauFonctionnel nf;
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
//        Evenement[] e = nf.getEvenements();
        
        
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
