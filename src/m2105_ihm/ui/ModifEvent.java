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
import static java.awt.image.ImageObserver.WIDTH;
/**
 *
 * @author juliette campos
 */
public class ModifEvent extends javax.swing.JPanel {
    
    /*
     * Attributs
     */
    
    private CarnetUI     carnet;
    private ZoneDessinUI zoneDessin;
    private JTable       evenements;
    private PlanningUI   planning;
    private JTextField   intitule;
    private JTextField   dateAnnee;
    private JComboBox    dateMois;
    private JTextField   dateJours;
    private  JButton     valider;
    private  JButton     annuler;
    
    
    
    
    
    
    
    

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
         intitule = new JTextField(30);
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
           
           this.valider=new JButton("VALIDER");
           boutons.add(valider);

           this.annuler=new JButton("ANNULER");
           boutons.add(annuler);
           this.add(boutons);
        
    }

    /**
     * Affecte des valeurs au formulaire pour un événement
     * @param Evenement un événement
     * @return
     */        
    public boolean setValues(Evenement event) {
        if (event == null) { return false; }            
                  
        
         intitule.setText(event.getIntitule());
       

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
        
              event.setIntitule(intitule.getText());    
              event.setDate(WIDTH, Mois.MARS, WIDTH);
               
        return true;
    }
}
