/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import m2105_ihm.nf.Mois;
import m2105_ihm.nf.Contact;
import m2105_ihm.nf.Evenement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import m2105_ihm.nf.*;
import m2105_ihm.ui.CarnetUI;
import java.awt.BorderLayout; 
import java.awt.*;
import java.awt.GridBagLayout; 
import java.awt.GridBagConstraints; 
/**
 *
 * @author juliette campos
 */
public class FicheEvtUI extends javax.swing.JPanel {
    
    /*
     * Attributs
     */
    
    private CarnetUI     carnet;
    private ZoneDessinUI zoneDessin;
    private JTable      evenements;
//    private DefaultTableModel model;
    private PlanningUI          planning;
    private JComboBox  intitule;
    private JTextField  dateAnnee;
    private JComboBox  dateMois;
    private JTextField   dateJours;
    private  JButton editer;
    private  JButton supprimer;
    private  JButton menuprincipal;
   
    
    
    
    
    
    
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
        
        this.setLayout(new GridLayout(0,2));
        JPanel boutons= new JPanel();
        JPanel date= new JPanel();
        
        
        
      
         this.add(new JLabel("EVENEMENTS"));
         this.add(new JLabel(""));
          this.add(new JLabel(""));
          this.add(new JLabel(""));
          
         
         this.add(new JLabel("Intitulé :"));
         intitule = new JComboBox();
         this.add(intitule);
         
         this.add(new JLabel("Date :"));
         
         
         this.add(date);
        
        date.setLayout(new GridLayout(0,3)); 
        dateJours = new JTextField(2);
        date.add( dateJours);
  
        dateMois= new JComboBox(Mois.values());
        date.add(dateMois);
        
        dateAnnee = new JTextField(6);
        date.add( dateAnnee);
        
        
           this.add(new JLabel("")); 
           
           this.editer=new JButton("EDITER");
           boutons.add(editer);

           this.supprimer=new JButton("SUPPRIMER");
           boutons.add(supprimer);
           
           this.menuprincipal=new JButton("MENU PRINCIPAL");
           boutons.add(menuprincipal);
           
           this.add(boutons);
           
           
        
    }

    /**
     * Affecte des valeurs au formulaire pour un événement
     * @param Evenement un événement
     * @return
     */        
    public boolean setValues(Evenement event) {
        if (event == null) { return false; }            
                  
        
         intitule.setSelectedItem(event.getIntitule());
       

        dateAnnee.setText(""+event.getDateAnnee());
        dateMois.setSelectedItem(event.getDateMois());
        dateJours.setText(""+event.getDateJour());
        
            
        return false;
    }

    /**
     * Retourne les valeurs associées au formulaire de fiche événement
     * @param Evenement événement à affecter
     * @return 
     */    
    public boolean getValues(Evenement event) {
        
        
        if (event == null) { return false; }
        
              event.setIntitule(TOOL_TIP_TEXT_KEY);    
              event.setDate(WIDTH, Mois.MARS, WIDTH);
               
        return true;
    }
}
