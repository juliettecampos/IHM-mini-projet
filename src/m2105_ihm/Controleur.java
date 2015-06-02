/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm;

import m2105_ihm.nf.Contact;
import m2105_ihm.nf.GroupeContacts;
import m2105_ihm.nf.NoyauFonctionnel;
import m2105_ihm.nf.Evenement;

import m2105_ihm.ui.CarnetUI;
import m2105_ihm.ui.FenetreUI;
import m2105_ihm.ui.PlanningUI;

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
        System.out.println("Action pour creer un contact");
    }

    /**
     * Action supprimer contact
     */
    public void supprimerContact() {
        System.out.println("Action pour supprimer un contact");
    }
    
    /**
     * Action créer un groupe de contacts
     */
    public void creerGroupe() {
        System.out.println("Action pour creer un groupe de contacts");
    }

    /**
     * Action supprimer un groupe de contacts
     */
    public void supprimerGroupe() {
        System.out.println("Action pour supprimer un groupe de contacts");
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
            fenetre.addTab(planningUI, "Planning");     // onglet carnet
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