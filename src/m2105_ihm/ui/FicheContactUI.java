/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.awt.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;
import m2105_ihm.nf.*;

/**
 *
 * @author IUT2
 */
public class FicheContactUI extends JPanel {

    private CarnetUI         carnet;
    private JPanel           panelInfo;
    
    private Integer[]        jours;
    private JPanel           paneldate;
    private JPanel           paneldateLabel;
    private JPanel           paneldatechamp;
    
    
    private JPanel           panelPreferences;
    
    private JComboBox        listeJours;
    private JComboBox        listeMois;
    private JComboBox        listeAnnees;
    private JComboBox        listeRegions;

    
    private JLabel           labelNom;
    private JLabel           labelPrenom;
    
    private JTextField       champNom;
    private JTextField       champPrenom;
    private JTextField       champTelephone;
    private JTextField       champMail;

    private HashMap<Hobby,JCheckBox> hashHobby;
    
    private JButton          valider;
    private JButton          annuler;
    
    
    public Integer[] listeJour(){
        Integer[] j = new Integer[31];
        for(int i=0; i<31; i++){
            j[i]=i+1;
        }
        return j;
    }
    /**
     * Formulaire pour saisir les informations relatives à un contact
     */
    public FicheContactUI(CarnetUI carnet) {
        super();
        
        
        
        
        this.carnet      = carnet;
              
        initUIComponents();
        initListeners();
    }
    
    /**
     * Crée et positionne les composants graphiques constituant l'interface
     */
    private void initUIComponents() {      
        /** Pour l'exemple **/
        
        /*
         * Ajoute dans l'IHM un champ pour la saisie/Affichage du nom
         */  
        panelInfo = new JPanel();
        paneldate = new JPanel();
        paneldateLabel = new JPanel();
        paneldatechamp = new JPanel();
        panelPreferences = new JPanel();
        
        panelInfo.setLayout(new GridBagLayout());
        paneldate.setLayout(new BorderLayout());
        panelPreferences.setLayout(new GridBagLayout());
        GridBagConstraints contraintes = new GridBagConstraints();
        //c.weightx=1;
        contraintes.ipady=5;
        contraintes.gridx=1;
        
        
        //////////////////////////////////////////
        ////////// PANEL INFO NOM PRENOM /////////
        //////////////////////////////////////////
        

        labelNom = new JLabel("Nom");
        champNom = new JTextField(30);
        
        
        labelPrenom = new JLabel("Prénom");
        champPrenom = new JTextField(30);
        
        panelInfo.add(labelNom,contraintes);
        panelInfo.add(champNom,contraintes);
        panelInfo.add(labelPrenom,contraintes);
        panelInfo.add(champPrenom,contraintes);
        
        /////////////////////
        //// PANEL DATE /////
        /////////////////////
        
        jours = listeJour();
        listeJours = new JComboBox(jours);
        
       
        Mois[] mois = Mois.values();
        
        listeMois = new JComboBox(mois);
        
        
       String[] annee = new String[150];
       
       for (int i=1865;i<2015;i++) {
           annee[i-1865] = Integer.toString(i);
       }
       listeAnnees = new JComboBox(annee);
       
       paneldateLabel.add(new JLabel("Jour"),BorderLayout.EAST);
       paneldateLabel.add(new JLabel("Mois"),BorderLayout.CENTER);
       paneldateLabel.add(new JLabel("Annee"),BorderLayout.WEST);
       
       paneldatechamp.add(listeJours,BorderLayout.EAST);
       paneldatechamp.add(listeMois,BorderLayout.CENTER);
       paneldatechamp.add(listeAnnees,BorderLayout.WEST);
       
       
       
        
        ////////////////////////////////////
        //////// PANEL PREFERENCES ////////
        ////////////////////////////////////
        
        
        champTelephone = new JTextField(30);
        
        
        champMail = new JTextField(30);
        
        
        Region[] reg = Region.values();
        listeRegions = new JComboBox(reg);
        
       
        
        
        panelPreferences.add(new JLabel("Telephone"),contraintes);
        panelPreferences.add(champTelephone,contraintes);
        
        panelPreferences.add(new JLabel("Mail"),contraintes);
        panelPreferences.add(champMail,contraintes);
        
        panelPreferences.add(new JLabel("Region"),contraintes);
        panelPreferences.add(listeRegions,contraintes);
        
        
        hashHobby = new HashMap<Hobby,JCheckBox>();
        
        for (Hobby h : Hobby.values()) {
            hashHobby.put(h, new JCheckBox(h.toString()));
            
            panelPreferences.add(hashHobby.get(h),contraintes);
            
        }
        
        //////////////////////////////
        //////// MISE EN PAGE ////////
        //////////////////////////////
        this.setLayout(new GridBagLayout());
        GridBagConstraints contrainte = new GridBagConstraints();
        contrainte.fill = GridBagConstraints.HORIZONTAL;
        contrainte.weightx=1;
        contrainte.ipady=100;
        contrainte.gridx=1;
        this.add(panelInfo, contrainte);
        contrainte.ipady=10;
        this.add(paneldate, contrainte);
        contrainte.ipady=80;
        this.add(panelPreferences, contrainte);
        
        paneldate.add(paneldateLabel,BorderLayout.NORTH);
        paneldate.add(paneldatechamp,BorderLayout.CENTER);
        
        valider = new JButton("APPLIQUER");
        panelPreferences.add(valider,contraintes);
        
        annuler = new JButton("ANNULER");
        panelPreferences.add(annuler,contraintes);
        
        
        
        
        
    }
    
    /**
     * Affecte des valeurs au formulaire de fiche contact
     * @param contact un contact pour mettre à jour à l'IHM
     * @return vrai si ok
     */
    public boolean setValues(Contact contact) {
        if (contact == null) { return false; }
        
        /** TP 1 : à compléter **/
        
        /*
         * Nom du contact
         */
        champNom.setText(contact.getNom()); 
        champPrenom.setText(contact.getPrenom());
        champTelephone.setText(contact.getNumeroTelephone());
        champMail.setText(contact.getEmail());
        listeJours.setSelectedItem(contact.getDateNaissanceJour());
        listeMois.setSelectedItem(contact.getDateNaissanceMois());
        listeAnnees.setSelectedItem("" +contact.getDateNaissanceAnnee());
        listeRegions.setSelectedItem(contact.getRegion());
        
        for(JCheckBox J : hashHobby.values()) {
            J.setSelected(false);
        }
        for (Hobby h : contact.getHobbies()) {
            hashHobby.get(h).setSelected(true);
        }
        
        return true;
    }
    
    /**
     * Retourne les valeurs associées au formulaire de fiche contact
     * @param contact un contact à mettre à jour à partir de l'IHM
     * @return vrai si ok
     */
    public boolean getValues(Contact contact) {
        if (contact == null) { return false; }
        
        /** TP 1 : à compléter **/
          
        /*
         * Affecte une valeur à l'attribut Nom avec le nom saisi dans le champ
         * correspondant de l'IHM
         */
        contact.setNom(champNom.getText());     
        contact.setPrenom(champPrenom.getText());
        
        int jour = listeJours.getSelectedIndex()+1;
        Mois mois = (Mois) listeMois.getSelectedItem();
        
        int annee = listeAnnees.getSelectedIndex() +1865;
        
        contact.setDateNaissance(jour,mois, annee);
        
        
        contact.setNumeroTelephone(champTelephone.getText());
        contact.setEmail(champMail.getText());
        contact.setRegion((Region) listeRegions.getSelectedItem());
        
        for (Hobby h : contact.getHobbies()) {
            contact.removeHobby(h);
        }
        for (Hobby h : hashHobby.keySet()) {
            if (hashHobby.get(h).isSelected()){
            contact.addHobby(h);
            }
        }
        
        
        

        return true;
    }
    
    /**
     * Initialise la gestion des événements
     */
    private void initListeners() {
        /** TP 5 : à compléter **/ 
        
        annuler.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                carnet.setContactModified(false);
            }
        });
        
        valider.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                carnet.setContactModified(true);
            }
            
            
        });
    }    
}