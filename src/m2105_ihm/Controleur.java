/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm;


import java.awt.Point;
import java.util.Scanner;
import javax.swing.JFrame;
import m2105_ihm.nf.*;
import m2105_ihm.nf.Mois;
import m2105_ihm.ui.*;



/**
 *
 * @author juliette campos
 */
public class Controleur {
    
    /*
     * Noyau Fonctionnel
     */    
    NoyauFonctionnel nf;
            
    /*
     * Composants
     */
    private CarnetUI carnetUI;
    private PlanningUI planningUI;
    private FenetreUI fenetre;

    /**
     * Constructeur de la fenêtre principale
     */
    public Controleur() {
        nf = new NoyauFonctionnel();
        
        initUI();
        initContent();
    }

    /**
     * Action créer un nouveau contact
     */
    public void creerContact() {
        Contact c = new Contact();

        c.setDateNaissance(2002, Mois.NOVEMBRE, 22);
        c.setNom("Votre nom");
        c.setPrenom("Votre prenom");
        c.setEmail("Votre mail");
        c.setNumeroTelephone("01 02 03 04 05");
        c.setRegion(Region.PACA);
                
        c.setDisponibilite(DispoSortie.NUIT);
        
        c.addHobby(Hobby.SPORT);
        c.addHobby(Hobby.LECTURE);
        carnetUI.ajouterContact(c);
        
        System.out.println("Action pour creer un contact");
        nf.addContact(c);
    }

    /**
     * Action supprimer contact
     */
    public void supprimerContact() {
        Contact c = carnetUI.getSelectedContact();
        BoiteDialogUI.afficherConfirmation(fenetre, c); 
        carnetUI.retirerContact(c);
        System.out.println("Action pour supprimer un contact");
        nf.removeContact(c);
    }
    
    /**
     * Action créer un groupe de contacts
     */
    public void creerGroupe() {
        
        GroupeContacts g = new GroupeContacts() ;  
        
        /* nom du groupe */
        g.setNom("Copains IUT2");

        /* symboles du groupe */ 
        g.addSymbole(Symbole.FLEUR);
        g.addSymbole(Symbole.ETOILE);
        g.addSymbole(Symbole.TABLEAU);
        
        /* 
         * Forme géometrique pour le logo du groupe (liste de points)
         * Pour l'exemple : un carré
         */
        Point [] points = new Point[4];
        
        points[0] = new Point(20,20);
        points[1] = new Point(100,20);
        points[2] = new Point(100,100);
        points[3] = new Point(20,100);
        
        g.setPoints(points);
       
        carnetUI.ajouterGroupe(g);

        System.out.println("Action pour creer un groupe");
        nf.addGroupe(g);
    }

    /**
     * Action supprimer un groupe de contacts
     */
    public void supprimerGroupe( ) {
        
        GroupeContacts g = carnetUI.getSelectedGroupe() ; 
        
       BoiteDialogUI.afficherConfirmation(fenetre, g); 
       carnetUI.retirerGroupe(g);
       System.out.println("Action pour supprimer un groupe");
       nf.removeGroupe(g);
        
    }
    
    /**
     * Création des composants constituant la fenêtre principale
     */
    private void initUI() {
        /* Onglets */
        carnetUI = new CarnetUI(this);
        planningUI=new PlanningUI(this);

        /* Fenêtre principale */
        fenetre = new FenetreUI(this);
        fenetre.addTab(carnetUI, "Carnet");     // onglet carnet
        fenetre.addTab(planningUI, "Planning");     // onglet planning
        fenetre.afficher();
    }
        
    /**
     * Met à jour la base de données
     */
    public void enregistrer() {
        nf.updateDB();
    }    
        
    /**
     * Quitter l'application sans enregistrer les modifications
     */
    public void quitter() {
        System.exit(0);
    }
        
    /**
     * Alimente la liste avec la liste des contacts existants
     */
    private void initContent() {
        for(Contact c : nf.getContacts()) {
            carnetUI.ajouterContact(c);
        }
        
        for(GroupeContacts g : nf.getGroupes()) {
            carnetUI.ajouterGroupe(g);
        }
        
        carnetUI.show();
    }
    
    public void setContactSelected(boolean selected) {
        fenetre.setMenuContactSelected(selected);
        
    }
    
    public void setEvtSelected(boolean selected) {
        fenetre.setMenuEvenementSelected(selected);
    }    
}