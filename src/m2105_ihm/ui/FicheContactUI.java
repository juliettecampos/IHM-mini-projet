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
    private Integer[]        jours;
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
    private JButton          modifier;
    
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
        JPanel identite = new JPanel();
        this.add(identite);
        JPanel date = new JPanel();
        JPanel hobby= new JPanel();
        JPanel boutons = new JPanel();
       
       
        
//        panelPreferences.setLayout(new GridBagLayout());
//        GridBagConstraints contraintes = new GridBagConstraints();
//     
//        contraintes.ipady=5;
//        contraintes.gridx=1;
//        
        
      
        identite.setLayout(new GridLayout(0,2));
        
            
        identite.add(labelNom = new JLabel("Nom : "));
        identite.add(champNom = new JTextField(30));
        
        
        identite.add(labelPrenom = new JLabel("Prénom : "));
        identite.add(champPrenom = new JTextField(30));
        
        
        
        
       
        identite.add(new JLabel("Date de naissance :"));
        date.setLayout(new GridLayout(0,3));
        identite.add(date);
        
        jours = listeJour();
        date.add(listeJours = new JComboBox(jours));
        
       
        Mois[] mois = Mois.values();
        date.add(listeMois = new JComboBox(mois));
        
        
       String[] annee = new String[150];
       
       for (int i=1865;i<2015;i++) {
           annee[i-1865] = Integer.toString(i);
       }
       date.add(listeAnnees = new JComboBox(annee));
       
      
        
        identite.add(new JLabel("Telephone :"));
        identite.add(champTelephone = new JTextField(30));
        
        
        identite.add(new JLabel("Mail : "));
        identite.add(champMail = new JTextField(30));
    
        identite.add(new JLabel("Region : "));
        Region[] reg = Region.values();
        identite.add(listeRegions = new JComboBox(reg));
       
        hobby.setLayout(new GridLayout(0,3));
        identite.add(hobby);
        
        identite.add(new JLabel("Hobby : ")); 
        hashHobby = new HashMap<Hobby,JCheckBox>();
        
        for (Hobby h : Hobby.values()) {
            hashHobby.put(h, new JCheckBox(h.toString()));
            
            hobby.add(hashHobby.get(h));
            
        }
        identite.add(hobby);

       
        valider = new JButton("APPLIQUER");
        boutons.add(valider);
        
        annuler = new JButton("ANNULER");
        boutons.add(annuler);
        
        identite.add(new JLabel(""));
        identite.add(new JLabel(""));
        identite.add(new JLabel(""));
        identite.add(new JLabel(""));
        identite.add(new JLabel(""));
       
        identite.add(boutons);
       
        
        
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