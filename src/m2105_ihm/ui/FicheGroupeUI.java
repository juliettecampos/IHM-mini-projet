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
    private CarnetUI carnet;
    private ZoneDessinUI zoneDessin;

    private JTextField champNomGroupe;
    private JList ListeSymbole; 
    private DefaultTableModel tableContact;
    
    private JButton erase;
    private JButton modifier;
    private JButton annuler;

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
        zoneDessin = new ZoneDessinUI();
        this.add(zoneDessin);

        erase = new javax.swing.JButton("Effacer le dessin");
        this.add(erase);

        this.add(new JLabel("Nom du groupe :"));
        champNomGroupe = new JTextField(30);
        this.add(champNomGroupe);

        this.add(new JLabel("Liste des contacts du groupe :"));

        String[] colonnes = {"Nom", "Prénom"};
        tableContact = new DefaultTableModel();
        tableContact.setColumnIdentifiers(colonnes); // intitulé des colonnes 

        JTable table = new JTable(tableContact);
        add(table.getTableHeader()); // Nom des colonnes 
        add(table);                  // Valeurs en ligne 

        this.add(new JLabel("Liste des symboles du groupe :"));
        ListeSymbole = new JList(Symbole.values()); 
        this.add(ListeSymbole);

        modifier = new javax.swing.JButton("MODIFIER");
        this.add(modifier);
        
        annuler = new javax.swing.JButton("ANNULER");
        this.add(annuler);

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
        
        int indices[] = new int[groupe.getSymboles().length];
        for (int i = 0; i < groupe.getSymboles().length; i++) indices[i] = groupe.getSymboles()[i].ordinal();
        ListeSymbole.setSelectedIndices(indices);
        
        tableContact.setRowCount(0);
        for (Contact c : groupe.getContacts()) {
            String tmp[] = {c.getNom(),c.getPrenom()};
            tableContact.addRow(tmp);
        } 
        
        zoneDessin.effacer();
        zoneDessin.setPoints(groupe.getPoints());
                
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
        
        for (Symbole s : Symbole.values()) {
            groupe.removeSymbole(s);
        }
        
        for (Object s : ListeSymbole.getSelectedValues()) {
            groupe.addSymbole((Symbole) s);
        }
       
        groupe.setPoints(zoneDessin.getPoints());
        
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
             @Override
            public void actionPerformed(ActionEvent e) {
               
                zoneDessin.effacer();
               
            }
        });
        
       
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
