/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.TreeSelectionModel;

import m2105_ihm.Controleur;
import m2105_ihm.nf.Contact;
import m2105_ihm.nf.Evenement;
import m2105_ihm.nf.Mois;
import m2105_ihm.nf.NoyauFonctionnel;

/**
 *
 * @author IUT2
 */
public class PlanningUI extends JPanel {
    /**
     * Creates new form CarnetUI
     */
    private Controleur       controleur;
    private FicheEvtUI       ficheEvt;
    private JPanel panelEvent;
    private NoyauFonctionnel nf;
    private JTextField champEvent;
    private DefaultTableModel tableContact;
        private Integer[]        jours;
private JComboBox listeJours;
private JComboBox listeMois;
private JComboBox listeAnnees;
private JPanel           paneldate;
    private JPanel           paneldateLabel;
    private JPanel           paneldatechamp;
    /** 
     * Constructeur : initialise les composants de l'IHM pour les événements
     * @param une instance du controleur
     */
    public PlanningUI(Controleur ctrl) {
        super();
        
        this.controleur = ctrl;
        
        initComponents();        
    }

    /**
     * Crée et place les composants graphiques de l'interface
     */
    private void initComponents() {
        /*
         * Fiche événement
         */    
        panelEvent = new JPanel();

        ficheEvt = new FicheEvtUI(this);
        this.add(new javax.swing.JLabel("Evenements"));
        
        Evenement[] e = controleur.nf.getEvenements();
        this.add(new JComboBox (e));
        
        String[] colonnes = {"Nom", "Prénom"};
        tableContact = new DefaultTableModel();
        tableContact.setColumnIdentifiers(colonnes); // intitulé des colonnes 

        JTable table = new JTable(tableContact);
        add(table.getTableHeader()); // Nom des colonnes 
        add(table);                  // Valeurs en ligne 
        
        //panel date
        paneldate = new JPanel();
        paneldate.setLayout(new BorderLayout());
        jours = listeJour();
        listeJours = new JComboBox(jours);
        
       
        Mois[] mois = Mois.values();
        
        listeMois = new JComboBox(mois);
        
        
        String[] annee = new String[96];
       
        for (int i=1920;i<=2015;i++) {
           annee[i-1920] = Integer.toString(i);
        }
        listeAnnees = new JComboBox(annee);
        
        this.add(new JLabel("Jour"));
        this.add(new JLabel("Mois"));
        this.add(new JLabel("Annee"));
       
        this.add(listeJours,BorderLayout.EAST);
        this.add(listeMois,BorderLayout.CENTER);
        this.add(listeAnnees,BorderLayout.WEST);
        
        
        
    }
    
    /**
     * Ajoute une entrée dans la liste de événements
     * @param title texte affiché dans la liste pour un contact
     * @param Contact objet contact associé
     */
    public boolean ajouterEvt(Evenement evt) {
        if (evt == null) { return false; }
        
        /** Projet à completer **/
            
        return true;
    }
    
    /**
     * Retire une entrée dans l'arbre pour les contacts
     * @param Contact contact à retirer
     */    
    public boolean retirerEvt(Evenement evt) {
        if (evt == null) { return false; }
        
        /** Projet à completer **/
        nf.removeEvenement(evt);
        return false;
    }
    
    /*
     * Retourne l'événement sélectionné
     */
    public Evenement getSelectedEvt() {    
        return null;    
    
    }
    
    
   public boolean setValues(Evenement e) {
//        if (e == null) { return false; }
//        tableContact.setRowCount(0);
//        for (Contact c : groupe.getContacts()) {
//            String tmp[] = {c.getNom(),c.getPrenom()};
//            tableContact.addRow(tmp);
//        } 
        return true;
        } 
       
       
    public Integer[] listeJour(){
        Integer[] j = new Integer[31];
        for(int i=0; i<31; i++){
            j[i]=i+1;
        }
        return j;
    }       
       
       
       
       
       
       
   }

