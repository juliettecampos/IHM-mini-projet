/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;
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
/*
 *
 * @author juliette campos
 */
public class FicheGroupeUI extends javax.swing.JPanel {
    /*
     * Composants graphiques constituants l'interface
     */
    private CarnetUI            carnet;
    private ZoneDessinUI        zoneDessin;
    private CarnetUI            carnetUI;
    private JComboBox           contact[];
    private JComboBox           symbol;
    private JTable              tableau;
    private DefaultTableModel   model;
    private  JButton            erase;
    private  JButton            annuler;
    private  JButton            modifier;


    /**
     * Creates new form CarnetUI
     */
    public FicheGroupeUI(CarnetUI carnet) { 
        super();
       
      
  
        initUIComponents();    
        initListeners();
        
    }

    /**
     * Crée et positionne les composants graphiques constituant l'interface
     */
    private void initUIComponents() {
        
        /** TP 2 : à compléter **/
          
       this.setLayout(new BorderLayout());
       
       
        JPanel symbole = new JPanel();
        this.add(symbole);
        
        JPanel contacts = new JPanel();
      
        JPanel global = new JPanel();
        this.add(global);
        
        JPanel effacer = new JPanel();
         
        JPanel modification = new JPanel();
        
        
            GridBagConstraints contrainte1 =new GridBagConstraints(); 
            this.add(global,BorderLayout.NORTH);
            global.setLayout(new GridLayout(0,2));   
            global.add(symbole);
                    
        
            
            symbole.setLayout(new GridBagLayout());
            
            contrainte1.gridx = 0; contrainte1.gridy = 0; 
            contrainte1.gridwidth =1 ; contrainte1.gridheight = 1; 
            contrainte1.fill = GridBagConstraints.BOTH ;
            
            symbole.add(new JLabel(" Contact:"), contrainte1); 
                                   
                    GridBagConstraints contrainte2 =new GridBagConstraints(); 

                    symbole.add(contacts,contrainte2);
                  
                
                     contacts.setLayout(new GridBagLayout()); 

                     contrainte2.gridx = 0; contrainte2.gridy = 0; 
                     contrainte2.gridwidth =1 ; contrainte2.gridheight = 1; 
                     contrainte2.fill = GridBagConstraints.BOTH ;
                     
                     contrainte1.gridy ++ ;
                    
                    
                     String [] colonnes =  { "Nom", "Prenom" }; 
                     model = new DefaultTableModel(); 
                     model.setColumnIdentifiers(colonnes);

                     JTable table = new JTable(model); 

                     contrainte2.gridy ++ ;
                     contrainte2.gridx ++;
                     contacts.add(table.getTableHeader(), contrainte2); 
                     contrainte2.gridy ++ ;
                     contacts.add(table,  contrainte2);


                   

                         contrainte1.gridy ++ ;
                         
                         symbole.add(new JLabel("Symboles :"),contrainte1);
                         symbol = new JComboBox(Symbole.values());
                         
                         contrainte1.gridx ++;
                         
                         symbole.add(symbol,contrainte1);
                         
                         contrainte1.gridx --;
                         contrainte1.gridy ++ ;
                
                         zoneDessin = new ZoneDessinUI();
                            this.add(zoneDessin);

                            erase = new javax.swing.JButton("Effacer le contenu de la zone de dessin");
                         
                         contrainte1.gridy ++ ;
                         contrainte1.gridx ++;
                         
                         symbole.add(zoneDessin,contrainte1);

               
                         contrainte1.gridy ++ ;
                         contrainte1.gridx --;
                         
                symbole.add(effacer,contrainte1);  
                         
                this.erase=new JButton("EFFACER");
                effacer.add(erase);
      
                contrainte1.gridy ++ ;
                contrainte1.gridx ++;
                        
                symbole.add(modification,contrainte1);
                
                this.annuler=new JButton("ANNULER");
                modification.add(annuler);
                                       
                this.modifier=new JButton("MODIFIER ");
                modification.add(modifier);
                

    }

    /**
     * Affecte des valeurs au formulaire de fiche groupe de contacts
     * @param groupe groupe de contacts
     * @return
    */    
    public boolean setValues(GroupeContacts groupe) {
        if (groupe == null) { return false; }
        
        /** TP 2 : à compléter **/
        
         
        model.setRowCount(0);
        for(Contact c : groupe.getContacts()) {
            String [] l =  { c.getNom(), c.getPrenom() };
                
           model.addRow(l);
                System.out.println(c.getNom());
        }
        
            
       
        zoneDessin.setPoints(groupe.getPoints());
        
        
         symbol.setSelectedItem(groupe.getSymboles());    
        
        
        return true;
    }
    
    /**
     * Retourne les valeurs associées au formulaire de fiche groupe de contacts
     * @return
     */    
    public boolean getValues(GroupeContacts groupe) {
        if (groupe == null) { return false; }
        
        /** TP 2 : à compléter **/
        
        /*
         * Ne pas s'occuper des membres du groupe car traité via des
         * commandes du menu qui appelera setValues
         */
        
        return true;
    }
    
    /**
     * Initialise la gestion des événements
     */
    public void initListeners() {        
        /*
         * Réagit aux évènements produits par le bouton effacer
         */ 
         erase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
                zoneDessin.effacer();
               
            }
        });
        
       
        annuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
                carnet.setGroupeModified(false);
               
            }
        });
         
        modifier.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
                carnet.setGroupeModified(true);
               
            }
        });
          
    }    
}
