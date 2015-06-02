
/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import m2105_ihm.Controleur;
        
/**
 *
 * @author juliette campos
 */
public class FenetreUI extends JFrame {
    /*
     * Composants
     */
    private JMenuItem [] menuFichier;
    private JMenuItem [] menuContacts;
    private JMenuItem [] menuEvenements; // à utiliser pour le mini projet    

    private JMenuBar barreMenu;
    private JMenuItem[] menuContact;
    
    /**
     * Constructeur de la fenêtre principale
     */
    public FenetreUI(Controleur controleur) {
        super("Gestion de contacts et d'évenements");
        
        this.controleur = controleur;
        
        menuFichier = new JMenuItem[2];
        menuContacts = new JMenuItem[6];
                
        initMenus();
        initUIComponents();
    }
    
    /**
     * Crée les menus de la fenêtre
     */
    private void initMenus() {
        
        barreMenu = new JMenuBar();     
        barreMenu.add(initMenuContacts());
        barreMenu.add(initMenuFichier());
        this.setJMenuBar(barreMenu);
        
    }

    /**
     * Crée un menu pour les fonction enregistrer et quitter
     */
    private JMenu initMenuFichier() {
        JMenu menu;
                
        menu = new JMenu("Fichier");
        
        /* Enregistrer */        
        menuFichier[0] = new JMenuItem("Enregistrer");
        menuFichier[0].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    controleur.enregistrer();
                }
            }
        );
        menu.add(menuFichier[0]);
        
        /* Quitter */
        menuFichier[1] = new JMenuItem("Quitter");
        menuFichier[1].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    controleur.quitter();
                }
            }
        );
        menu.add(menuFichier[1]);
        
        return menu;
    }
        
    /**
     * Crée un menu pour la gestion des contacts et groupes de contacts
     * @return 
     */
    private JMenu initMenuContacts() {
        JMenu menu = new JMenu("Contacts");
        menuContacts[0] = new JMenuItem("Créer Contact");
        menuContacts[0].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    controleur.creerContact();
                }
            }
        );
        menu.add(menuContacts[0]);
        menuContacts[1] = new JMenuItem("Créer Groupe");  
        menuContacts[1].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    controleur.creerGroupe();
                }
            }
        );
        menu.add(menuContacts[1]);
        menuContacts[2] = new JMenuItem("Supprimer Contact");  
        menuContacts[2].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    controleur.supprimerContact();
                }
            }
        );
        menu.add(menuContacts[2]);
        menuContacts[3] = new JMenuItem("Supprimer Groupe");  
        menuContacts[3].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    controleur.supprimerGroupe();
                }
            }
        );
        menu.add(menuContacts[3]);
        return menu;
    }

    /**
     * Crée un menu pour la gestion des évènements
     */
    private JMenu initMenuEvenements() {
        
        /* A compléter mini Projet */
        
        return null;
    }    
    
    /**
     * Rend visible la fenetre
     */
    public void afficher() {
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        setSize(900, 900);
        setVisible(true);                        
    }
    
    /**
     * Ajoute un onglet au contenu de la fentre
     * @param onglet un panel a ajouter
     * @param titre titre de l'onglet
     */
    public void addTab(JPanel onglet, String titre) {
        tabs.addTab(titre, onglet);
    }    
    
    /**
    * Indique si un contact ou un groupe est sélectionné
    * @param selected vrai si un contact est sélectionné
    */
    public void setMenuContactSelected(boolean selected) {
        this.contactSelected = selected;
        setMenuEnabled(MENU_CONTACTS, true);
        setMenuEnabled(MENU_EVENEMENTS, false);
    }

    /**
    * Indique si un événement est sélectionné
    * @param selected vrai si un événement est sélectionné
    */
    public void setMenuEvenementSelected(boolean selected) {
        this.evtSelected = selected;
        setMenuEnabled(MENU_CONTACTS, false);
        setMenuEnabled(MENU_EVENEMENTS, true);
    }

    /**
     * Création des composants constituant la fenêtre principale
     */
    private void initUIComponents() {
        /*
         * Contenu avec des onglets
         */
        tabs = new JTabbedPane();
        tabs.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (tabs.getSelectedIndex() == 0) {
                    setMenuEnabled(MENU_CONTACTS, true);
                    setMenuEnabled(MENU_EVENEMENTS, false);                    
                } else {
                    setMenuEnabled(MENU_CONTACTS, false);
                    setMenuEnabled(MENU_EVENEMENTS, true);                                        
                }
            }
        });
        
        setLayout(new BorderLayout());        
        add(tabs, BorderLayout.CENTER);
        setMenuEnabled(MENU_CONTACTS, true);
        setMenuEnabled(MENU_EVENEMENTS, false);
        
        /*this.add(new JLabel("Nord"),BorderLayout.NORTH); 
        this.add(new JLabel("Sud"),BorderLayout.SOUTH); 
        this.add(new JLabel("Centre"),BorderLayout.CENTER);
        this.add(new JLabel("Est"),BorderLayout.EAST); 
        this.add(new JLabel("Ouest"),BorderLayout.WEST); 
      
        
        this.getContentPane().add(new JButton("CENTER"), BorderLayout.CENTER);
        this.getContentPane().add(new JButton("NORTH"), BorderLayout.NORTH);
    */
    
    }
    /**
    * Commutation dans les panneaux
    */
    private void setMenuEnabled(int menuId, boolean enabled) {
        switch(menuId) {
            case MENU_CONTACTS:
                 setMenuEntryEnabled(MENU_CONTACTS, 0, enabled);
                 setMenuEntryEnabled(MENU_CONTACTS, 1, enabled);
                 setMenuEntryEnabled(MENU_CONTACTS, 2, enabled &    contactSelected);
                 setMenuEntryEnabled(MENU_CONTACTS, 3, enabled & (! contactSelected));
                 setMenuEntryEnabled(MENU_CONTACTS, 4, enabled &    contactSelected);
                 setMenuEntryEnabled(MENU_CONTACTS, 5, enabled &    contactSelected);                 
                 break;

            case MENU_EVENEMENTS:
                 setMenuEntryEnabled(MENU_EVENEMENTS, 0, enabled);
                 setMenuEntryEnabled(MENU_EVENEMENTS, 1, enabled & evtSelected);
                 setMenuEntryEnabled(MENU_EVENEMENTS, 2, enabled & evtSelected);
                 setMenuEntryEnabled(MENU_EVENEMENTS, 3, enabled & evtSelected);            
                 break;                
        }
    }
    
    /**
     * Active des entrees des menus
     */
    private void setMenuEntryEnabled(int menu, int index, boolean enabled) {
        switch(menu) {
            case MENU_FICHIER:
                if ((index >= 0) && (index < menuFichier.length)) {
                    if (menuFichier[index] != null) {
                        menuFichier[index].setEnabled(enabled);
                    }
                }
                break;
                
            case MENU_CONTACTS:
                if ((index >= 0) && (index < menuContacts.length)) {
                    if (menuContacts[index] != null) {
                        menuContacts[index].setEnabled(enabled);
                    }
                }
                break;
                
            case MENU_EVENEMENTS:
                if ((menuEvenements != null) && (index >= 0) && (index < menuEvenements.length)) {
                    if (menuEvenements[index] != null) {
                        menuEvenements[index].setEnabled(enabled);
                    }
                }
                break;
        }
    }            

    private JTabbedPane tabs;    
    private Controleur controleur;    
    private boolean contactSelected = true;
    private boolean evtSelected = false;    
    private static final int MENU_FICHIER    = 0;
    private static final int MENU_CONTACTS   = 1;
    private static final int MENU_EVENEMENTS = 2;        
}
