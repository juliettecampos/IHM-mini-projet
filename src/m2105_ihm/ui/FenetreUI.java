
/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import m2105_ihm.Controleur;
import m2105_ihm.nf.GroupeContacts;
        
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
    private JMenuItem [] menuEvenements;
    private JMenuItem [] menuGroupe;// à utiliser pour le mini projet    

    private JMenuBar     barreMenu;
    
    /**
     * Constructeur de la fenêtre principale
     */
    public FenetreUI(Controleur controleur) {
        super("Gestion de contacts et d'évenements");
        
        this.controleur = controleur;
        
        menuFichier = new JMenuItem[2];
        menuContacts = new JMenuItem[2];
        menuEvenements = new JMenuItem[4];
        menuGroupe = new JMenuItem[4];

        initMenus();
        initUIComponents();
    }
    
    /**
     * Crée les menus de la fenêtre
     */
    private void initMenus() {
        
        barreMenu = new JMenuBar();     
        barreMenu.add(initMenuContacts());
        barreMenu.add(initMenuGroupe());
        barreMenu.add(initMenuEvenements());
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
        menuFichier[0].setMnemonic(KeyEvent.VK_S);
        menuFichier[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
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
        menuFichier[1].setMnemonic(KeyEvent.VK_Q);
        menuFichier[1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
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
        menuContacts[0] = new JMenuItem("Créer contact");
        menuContacts[0].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    controleur.creerContact();
                }
            }
        );
        menu.add(menuContacts[0]);
        
        menuContacts[1] = new JMenuItem("Supprimer contact");  
        menuContacts[1].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    controleur.supprimerContact();
                }
            }
        );
        menu.add(menuContacts[1]);
        
        
        return menu;
    }

    /**
     * Crée un menu pour la gestion des évènements
     */
    private JMenu initMenuEvenements() {
        
        /* A compléter mini Projet */
        JMenu menu = new JMenu("Evenements");
        
        menuEvenements[0] = new JMenuItem("Créer un évenement");
        menuEvenements[0].addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controleur.creerEvenement();
            }
        });
        menu.add(menuEvenements[0]);
        
        menuEvenements[1] = new JMenuItem("Supprimer évenement");  
        menuEvenements[1].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    controleur.supprimerEvenement();
                }
            }
        );
        menu.add(menuEvenements[1]);
        menu.addSeparator();
        menuEvenements[2] = new JMenuItem("Ajouter un participant a l'evenement");
        menuEvenements[2].addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 controleur.ajouterParticipantEvenement();
             }
         });
        menu.add(menuEvenements[2]);
        return menu;
    }    
    
    
    private JMenu initMenuGroupe() {
        
        JMenu menu = new JMenu("Groupe");

        
        menuGroupe[0] = new JMenuItem("Créer Groupe");  
        menuGroupe[0].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    controleur.creerGroupe();
                }
            }
        );
        menu.add(menuGroupe[0]);
        
        menuGroupe[1] = new JMenuItem("Supprimer Groupe");  
        menuGroupe[1].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
            
                    controleur.supprimerGroupe();
                }
            }
        );
        menu.add(menuGroupe[1]);
        
        menu.addSeparator();
        menuGroupe[2] = new JMenuItem("Ajouter un contact au groupe");
        menuGroupe[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controleur.ajouterContactGroupe();
            }
        }
        );
        menu.add(menuGroupe[2]);

        menuGroupe[3] = new JMenuItem("Retirer un contact au groupe");
        menuGroupe[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controleur.retirerContactGroupe();
            }
        }
        );
        menu.add(menuGroupe[3]);    
    return menu;
    }
    
    
    
    
    
    
    
    /**
     * Rend visible la fenetre
     */
    public void afficher() {
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);
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
        setMenuEnabled(MENU_GROUPE,true);
        setMenuEnabled(MENU_EVENEMENTS, false);
    }

    /**
    * Indique si un événement est sélectionné
    * @param selected vrai si un événement est sélectionné
    */
    public void setMenuEvenementSelected(boolean selected) {
        this.evenementSelected = selected;
        setMenuEnabled(MENU_CONTACTS, false);
        setMenuEnabled(MENU_GROUPE,false);
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
                    setMenuEnabled(MENU_GROUPE,true);
                } else {
                    setMenuEnabled(MENU_CONTACTS, false);
                    
                    setMenuEnabled(MENU_GROUPE,false);
                    setMenuEnabled(MENU_EVENEMENTS, true); 

                }
            }
        });
        
        setLayout(new BorderLayout());        
        add(tabs, BorderLayout.CENTER);
        setMenuEnabled(MENU_CONTACTS, true);
        setMenuEnabled(MENU_GROUPE,true);
        setMenuEnabled(MENU_EVENEMENTS, true);
        
        
    
    }
    /**
    * Commutation dans les panneaux
    */
    private void setMenuEnabled(int menuId, boolean enabled) {
        switch(menuId) {
            case MENU_CONTACTS:
                 setMenuEntryEnabled(MENU_CONTACTS, 0, enabled);
                 setMenuEntryEnabled(MENU_CONTACTS, 1, enabled);
                 break;
            case MENU_GROUPE:
                setMenuEntryEnabled(MENU_GROUPE, 0, enabled  &    groupeSelected);
                 setMenuEntryEnabled(MENU_GROUPE, 1, enabled &    groupeSelected);
                 setMenuEntryEnabled(MENU_GROUPE, 2, enabled &    contactSelected);
                 setMenuEntryEnabled(MENU_GROUPE, 3, enabled &    contactSelected);         
            case MENU_EVENEMENTS:
                 setMenuEntryEnabled(MENU_EVENEMENTS, 0, enabled);
                 setMenuEntryEnabled(MENU_EVENEMENTS, 1, enabled );
                 setMenuEntryEnabled(MENU_EVENEMENTS, 2, true & contactSelected);            
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
                
            case MENU_GROUPE:
                if ((index >= 0) && (index < menuGroupe.length)) {
                    if (menuGroupe[index] != null) {
                        menuGroupe[index].setEnabled(enabled);
                    }
                }
                break;
            case MENU_EVENEMENTS:
                if ((index >= 0) && (index < menuEvenements.length)) {
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
    private boolean groupeSelected = true;
    private boolean evenementSelected = false;    
    private static final int MENU_FICHIER    = 0;
    private static final int MENU_CONTACTS   = 1;
    private static final int MENU_EVENEMENTS = 2;        
    private static final int MENU_GROUPE = 3;
    
}
