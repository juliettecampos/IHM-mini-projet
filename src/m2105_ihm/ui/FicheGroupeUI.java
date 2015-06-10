/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.table.DefaultTableModel;
import m2105_ihm.nf.*;
import m2105_ihm.ui.CarnetUI;
import java.awt.BorderLayout; 
import java.awt.*;
import java.awt.GridBagLayout; 
import java.awt.GridBagConstraints; 
import java.util.*;
import javax.swing.*;
/*
 *
 * @author juliette campos
 */
public class FicheGroupeUI extends javax.swing.JPanel {
    /*
     * Composants graphiques constituants l'interface
     */
    private CarnetUI             carnet;
    private JTextField           champNomGroupe;
    private JList                ListeSymbole; 
    private DefaultTableModel    model;
    private HashMap<Symbole,JCheckBox> hashSymbole;
    private JButton              erase;
    private JButton              modifier;
    private JButton              annuler;

    /**
     * Creates new form CarnetUI
     */
    public FicheGroupeUI(CarnetUI carnet) {
        super();

        this.carnet = carnet;

        initUIComponents();
        initListeners();
    }

    /**
     * Crée et positionne les composants graphiques constituant l'interface
     */
    private void initUIComponents() {

        /**
         * TP 2 : à compléter *
         */
        
       
      
        this.setLayout(new BorderLayout());
        
        JPanel nom = new JPanel();
        JPanel global = new JPanel();
        JPanel symbole = new JPanel();
        JPanel symboles = new JPanel();
        JPanel contacts = new JPanel();
        JPanel boutons = new JPanel();
        
        
        
           
        
            global.setLayout(new GridLayout(0,2)); 
            this.add(global,BorderLayout.NORTH);
            
            
           
            nom.add(champNomGroupe = new JTextField(10));
            global.add(nom);
            global.add(new JLabel(""));
           
              
            
            global.add(new JLabel("Symboles :"));
            
            hashSymbole = new HashMap<Symbole,JCheckBox>();
               for (Symbole s : Symbole.values()) {
                   
                    hashSymbole.put(s, new JCheckBox(s.toString()));
                     symboles.add(hashSymbole.get(s));
            
                 }
              global.add(symboles);
          
                   

                    global.add(new JLabel(" Contact:")); 
                    
                    symbole.setLayout(new GridBagLayout());
                    
                    GridBagConstraints contrainte1 =new GridBagConstraints();
                    
                            contrainte1.gridx = 0; contrainte1.gridy = 0; 
                            contrainte1.gridwidth =1 ; contrainte1.gridheight = 1; 
                            contrainte1.fill = GridBagConstraints.BOTH ;

                    
                                   
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
                     
                 global.add(symbole);

        
        modifier = new javax.swing.JButton("APPLIQUER");
        boutons.add(modifier);

        annuler = new javax.swing.JButton("ANNULER");
        boutons.add(annuler);
        global.add(new JLabel(""));
        global.add(new JLabel(""));
        global.add(new JLabel(""));
        global.add(new JLabel(""));
        global.add(new JLabel(""));
        global.add(boutons);     
    }


    /**
     * Affecte des valeurs au formulaire de fiche groupe de contacts
     * @param groupe groupe de contacts
     * @return
    */    
    public boolean setValues(GroupeContacts groupe) {
        if (groupe == null) { return false; }
        
        /** TP 2 : à compléter **/
        
        champNomGroupe.setText(groupe.getNom());
        
         for(JCheckBox J : hashSymbole.values()) {
            J.setSelected(false);
        }
        for (Symbole s : groupe.getSymboles()) {
            hashSymbole.get(s).setSelected(true);
        }
        
//        
//        int indices[] = new int[groupe.getSymboles().length];
//        for (int i = 0; i < groupe.getSymboles().length; i++) indices[i] = groupe.getSymboles()[i].ordinal();
//        ListeSymbole.setSelectedIndices(indices);
        
        model.setRowCount(0);
        for (Contact c : groupe.getContacts()) {
            String tmp[] = {c.getNom(),c.getPrenom()};
            model.addRow(tmp);
        } 
        
        
                
        return true;
            
        
    }
    
    /**
     * Retourne les valeurs associées au formulaire de fiche groupe de contacts
     * @return
     */    
    public boolean getValues(GroupeContacts groupe) {
        if (groupe == null) { return false; }
        
        /**
         * TP 2 : à compléter *
         */
        /*
         * Ne pas s'occuper des membres du groupe car traité via des
         * commandes du menu qui appelera setValues
         */
                
        groupe.setNom(champNomGroupe.getText());
                
          for (Symbole s : groupe.getSymboles()) {
            groupe.removeSymbole(s);
        }
        for (Symbole s : hashSymbole.keySet()) {
            if (hashSymbole.get(s).isSelected()){
            groupe.addSymbole(s);
            }
        
        
         
      
        }
        
        
        

        
        return true;
    }
    
    /**
     * Initialise la gestion des événements
     */
    public void initListeners() {        
        /*
         * Réagit aux évènements produits par le bouton effacer
         */ 
       
        annuler.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                carnet.setGroupeModified(false);
            }
        });
        modifier.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                carnet.setGroupeModified(true);
            }
            
            
        });
          
    }    
}
