/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.TreeSelectionModel;

import m2105_ihm.Controleur;
import m2105_ihm.nf.Evenement;

/**
 *
 * @author IUT2
 */
public class PlanningUI extends JPanel {
    /**
     * Creates new form CarnetUI
     */
    private CardLayout fiches;
    private JPanel cardPanel;
    public Controleur controleur;
    private FicheEvtUI ficheEvt;
    private ListeEvenement listeEvenement;    
    private JButton valider;

    /** 
     * Constructeur : initialise les composants de l'IHM pour les événements
     * @param une instance du controleur
     */
    public PlanningUI(Controleur ctrl) {
        super();
        
        this.controleur = ctrl;
        initComponents();        
    }

    /**
     * Crée et place les composants graphiques de l'interface
     */
    private void initComponents() {
        /*
         * Fiche événement
         */        
        
//     
//        this.add(new javax.swing.JLabel("Evenements"));  
//        Evenement[] e = controleur.nf.getEvenements();
//        this.add(new JComboBox (e));
//        JPanel boutons = new JPanel();
//      
//      
//       valider = new javax.swing.JButton("VALIDER");
//       boutons.add(valider);
//       this.add(boutons);
//       
        
        listeEvenement = new ListeEvenement(this);
        listeEvenement.setBorder(BorderFactory.createTitledBorder("Evenement"));
 
        
        
        JPanel vide = new JPanel();        
        ficheEvt = new FicheEvtUI(this);
   

        fiches = new CardLayout();
        fiches.addLayoutComponent(ficheEvt, "Evenement");
        fiches.addLayoutComponent(vide, "vide");
          
        cardPanel = new JPanel();
        cardPanel.setLayout(fiches);
        cardPanel.add(ficheEvt, "Evenement");
        cardPanel.add(vide, "vide");
        
        setLayout(new BorderLayout());
        add(listeEvenement, BorderLayout.WEST);
        add(cardPanel, BorderLayout.CENTER); 
       
       
    }
    
    /**
     * Ajoute une entrée dans la liste de événements
     * @param title texte affiché dans la liste pour un contact
     * @param Contact objet contact associé
     */
    public boolean ajouterEvt(Evenement evt) {
        return listeEvenement.ajouterEvenement(evt);

    }
    
    /**
     * Retire une entrée dans l'arbre pour les contacts
     * @param Contact contact à retirer
     */    
    public boolean retirerEvt(Evenement evt) {
        
   
       return listeEvenement.retirerEvenement(evt);
       
    }
    
    /*
     * Retourne l'événement sélectionné
     */
    public Evenement getSelectedEvt() {    
        
        /** Projet à completer **/
    return listeEvenement.getSelectedEvenement();
    }
    
    
     public void setSelectedItem(Object item) {
        if (item == null) {
            fiches.show(cardPanel,"vide");
        } else {
            if (item instanceof Evenement) {
                controleur.setContactSelected(true);
                ficheEvt.setValues((Evenement) item); // affiche les données du contact                 
                fiches.show(cardPanel,"Evenement");                
             
            }
        }
    }   
    
}
