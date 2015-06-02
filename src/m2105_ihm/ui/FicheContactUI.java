/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import m2105_ihm.nf.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import m2105_ihm.ui.CarnetUI;
import java.awt.BorderLayout; 
import java.awt.GridLayout;
/**
 *
 * @author juliette campos
 */
public class FicheContactUI extends JPanel {

    private CarnetUI         carnet;
    private JTextField       champNom;
    private JTextField       champPrenom; 
    private JTextField       champMail; 
    private JTextField       champTelephone;  
    private JTextField       champDatedenaissanceAnnee;
    private  JComboBox      champDatedenaissanceMois;
    private JTextField       champDatedenaissanceJours;
    private JCheckBox        champHobby[];  
    private JCheckBox       champDisposorties[]; 
    private JComboBox        champRegions; 
    
    
    
     
    
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
        
       this.setLayout(new BorderLayout());
       
       
        JPanel identite = new JPanel();
        this.add(identite);
        
        JPanel preferences = new JPanel();
        this.add(preferences);
        
        JPanel boutons= new JPanel();
        this.add(boutons);
        
        JPanel date= new JPanel();
       
        
        
        
        this.add(identite,BorderLayout.NORTH); 
        identite.setLayout(new GridLayout(0,2)); 
       
        
 
        identite.add(new JLabel("Nom :"));
        champNom = new JTextField(30);
        identite.add(champNom);
        
       
        identite.add(new JLabel("Prenom :"));
        champPrenom = new JTextField(30);
        identite.add(champPrenom);        
        
       
        
        identite.add(new JLabel("Mail :"));
         champMail = new JTextField(30);
        identite.add(champMail);
        
        
        identite.add(new JLabel("Telephone :"));
        champTelephone = new JTextField(30);
        identite.add(champTelephone);
         
        
        
        
        identite.add(new JLabel("Date de naissance :"));
        identite.add(date);
        
        date.setLayout(new GridLayout(0,3)); 
         
        champDatedenaissanceJours = new JTextField(2);
        date.add( champDatedenaissanceJours);
  
        champDatedenaissanceMois= new JComboBox(Mois.values());
        date.add(champDatedenaissanceMois);
        
        champDatedenaissanceAnnee = new JTextField(6);
        date.add( champDatedenaissanceAnnee);

     

    
        
          this.add(preferences,BorderLayout.WEST); 
          
         preferences.setLayout(new GridLayout(0,2)); 
         
         preferences.add(new JLabel("Hobbies :"));
         preferences.add(new JLabel(""));
         champHobby=new JCheckBox[Hobby.values().length];
       
        for(Hobby hobby : Hobby.values()) {
         
            champHobby[hobby.ordinal()]=new JCheckBox(hobby.toString());
            preferences.add(champHobby[hobby.ordinal()]);
        
        }
       
        
    
       
         preferences.add(new JLabel("Disponibilité :"));
         preferences.add(new JLabel(""));
         champDisposorties= new JCheckBox[Mois.values().length];
         

        for(DispoSortie dispo : DispoSortie.values()) {
          
            champDisposorties[dispo.ordinal()]=new JCheckBox( dispo.toString());
             preferences.add(champDisposorties[dispo.ordinal()]);
             preferences.add(new JLabel(""));
        }
         

      
         preferences.add(new JLabel("Regions :"));
       
        champRegions = new JComboBox(Region.values());
         preferences.add(champRegions);
        
         
         
         
         this.add(boutons,BorderLayout.SOUTH); 
         boutons.setLayout(new BorderLayout()); 
        

        
      
    }
    
    /**
     * Affecte des valeurs au formulaire de fiche contact
     * @param contact un contact pour mettre à jour à l'IHM
     * @return vrai si ok
     */
    public boolean setValues(Contact contact) {
        if (contact == null) { return false; }
       
        champNom.setText(contact.getNom());  
        champPrenom.setText(contact.getPrenom());
        champMail.setText(contact.getEmail());
        champTelephone.setText(contact.getNumeroTelephone());
        champDatedenaissanceAnnee.setText(""+contact.getDateNaissanceAnnee());
        champDatedenaissanceMois.setSelectedItem(contact.getDateNaissanceMois());
        champDatedenaissanceJours.setText(""+contact.getDateNaissanceJour());
        
        
         for(Hobby hobby : contact.getHobbies() ) {
            champHobby[ hobby.ordinal() ].setSelected(true);
            
        }
      
         
            champDisposorties[contact.getDisponibilite().ordinal()].setSelected(true);
             
         
       
          champRegions.setSelectedItem(contact.getRegion());
        
        
        
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
        contact.setEmail(champMail.getText());
        contact.setNumeroTelephone(champTelephone.getText());
        contact.setDateNaissance(contact.getDateNaissanceJour(), contact.getDateNaissanceMois(), contact.getDateNaissanceAnnee());
       contact.setRegion(contact.getRegion());


        return true;
    }
    
    /**
     * Initialise la gestion des événements
     */
    private void initListeners() {
        /** TP 5 : à compléter **/ 
    }    
}