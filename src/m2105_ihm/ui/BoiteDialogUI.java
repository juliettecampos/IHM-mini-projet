/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m2105_ihm.ui;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import m2105_ihm.nf.Contact;
import m2105_ihm.nf.Evenement;
import m2105_ihm.nf.GroupeContacts;
import m2105_ihm.nf.NoyauFonctionnel;

/**
 *
 * @author juliette campos
 */
public class BoiteDialogUI {

    /**
     * Boîte de dialogue pour confirmer la suppression d'un contact
     *
     * @param c un contact
     * @return vrai si confirmé
     */
    public static boolean afficherConfirmation(JFrame fenetre, Contact c) {
        boolean res = false;

        if (c != null) {
            String[] choix = new String[]{"Supprimer", "Annuler"};

            Object selectedValue = JOptionPane.showOptionDialog(fenetre,
                    "Voulez-vous vraiment supprimer le contact : "
                    + c.getPrenom() + " " + c.getNom() + " ?",
                    "Suppression d'un contact",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    choix,
                    choix[1]);
            res = (((Integer) selectedValue) == 0);
        }

        return res;
    }

    /**
     * Boîte de dialogue pour confirmer la suppression d'un groupe de contacts
     *
     * @param g un groupe de contacts
     * @return vrai si confirmé
     */
    public static boolean afficherConfirmation(JFrame fenetre, GroupeContacts g) {
        boolean res = false;

        if (g != null) {
            String[] choix = new String[]{"Supprimer", "Annuler"};

            Object selectedValue = JOptionPane.showOptionDialog(fenetre,
                    "Voulez-vous vraiment supprimer le groupe : "
                    + g.getNom() + " ?",
                    "Suppression d'un groupe",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    choix,
                    choix[1]);
            res = (((Integer) selectedValue) == 0);
        }

        return res;
    }

    /**
     * Boîte de dialogue pour confirmer la suppression d'un événement
     *
     * @param e un événement
     * @return vrai si confirmé
     */
    public static boolean afficherConfirmation(JFrame fenetre, Evenement e) {
        boolean res = false;

        if (e != null) {
            String[] choix = new String[]{"Supprimer", "Annuler"};

            Object selectedValue = JOptionPane.showOptionDialog(fenetre,
                    "Voulez-vous vraiment supprimer l'événement : "
                    + e.getIntitule() + " ?",
                    "Suppression d'un événement",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    choix,
                    choix[1]);
            res = (((Integer) selectedValue) == 0);
        }

        return res;
    }

    /**
     * Boîte de dialogue choisir un groupe auquel ajouter un contact
     *
     * @param titre titre de la fenêtre
     * @param groupes liste des groupes existants
     * @return groupe choisi sinon valeur null
     */
    public static GroupeContacts afficherChoixMembreContact(JFrame fenetre, String titre, GroupeContacts[] groupes) {
        GroupeContacts res = null;
        if (titre == null) {
            titre = "";
        }

        /** TP5 : à compléter **/
        if (groupes != null) {
            
            Object selectedValue = JOptionPane.showInputDialog(fenetre,
                  titre, 
                  "Choix d'un groupe",
                  JOptionPane.DEFAULT_OPTION, 
                  null,
                  groupes
                  , groupes[0]);
            
            res = (GroupeContacts) selectedValue;
            
        }        return res;
    }

    public static ArrayList<Contact> afficherParticipants(JFrame fenetre, Evenement e, Contact[] c) {
        
        ArrayList<Contact> res = new ArrayList();

        if (e != null) {
            String[] choix = new String[]{"Valider", "Annuler"};

            JPanel panel = new JPanel();

            panel.add(new JLabel("Sélectionner les contacts qui participent à l'événement"));

            JList ListeParticipants = new JList(c);
            panel.add(ListeParticipants);

            int[] indices = new int[e.getParticipants().length];
            int j = 0;
            for (int i = 0; i < c.length; i++) {
                for (Contact co : e.getParticipants()) {
                    if (c[i] == co) {
                        indices[j] = i;
                        j++;
                        break;
                    }
                }
            }
            ListeParticipants.setSelectedIndices(indices);
            
            for (Object ct : ListeParticipants.getSelectedValues()) {
                    res.add((Contact) ct);
                }

            int b = JOptionPane.showOptionDialog(fenetre, panel, "Modifier les participants", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, choix, choix[1]);

            if (b == 0) {
                res.removeAll(res);
                for (Object ct : ListeParticipants.getSelectedValues()) {
                    res.add((Contact) ct);
                }
            }
        }

        return res;
    }
    
    public static Evenement afficherChoixEventContact(JFrame fenetre, String titre,Evenement[] e) {
        Evenement res = null;
        if (titre == null) { titre = ""; }
            //String[] choix = new String[]{"Ajouter", "Annuler"};
            Object selectedValue = JOptionPane.showInputDialog(fenetre, res, "Ajout d'un contact dans un evenement",
                    JOptionPane.DEFAULT_OPTION, null, e , e[0]);
            
        /** TP5 : à compléter **/
            res = (Evenement)selectedValue;
        return res;
    }
}
