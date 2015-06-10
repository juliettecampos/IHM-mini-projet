/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Enumeration;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import m2105_ihm.nf.Contact;
import m2105_ihm.nf.GroupeContacts;

import java.awt.Component;
import javax.swing.UIManager;
import javax.swing.tree.DefaultTreeCellRenderer;
import m2105_ihm.nf.Evenement;
/**
 *
 * @author juliette campos
 */
public class ListeEvenement extends javax.swing.JPanel  {
    /*
     * Principaux élements de l'arbre
     */
    private JTree                  listeEvenement;
    private JScrollPane            listeDefilante;           
    private TreePath               selected;    
    private DefaultMutableTreeNode listeRacine;
    private DefaultMutableTreeNode listeNoeudEvenement;

    private PlanningUI planning;    
    
    
    // this is what you want
    private static class MyTreeCellRenderer extends DefaultTreeCellRenderer {

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

            // decide what icons you want by examining the node
            if (value instanceof DefaultMutableTreeNode) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
                
                if (node.getUserObject() instanceof NodeData) {
                    // your root node, since you just put a String as a user obj                    
                    setIcon(null);
                } else {
                    setIcon(UIManager.getIcon("FileChooser.homeFolderIcon"));
                }    
            }

            return this;
        }

    }
    /*
     * Classe de données spécifiques pour les entrées dans l'arbre
     */
    private class NodeData {
        public String title;
        public Object id;
        
        public NodeData(String title, Object id) {
            this.title = title;
            this.id = id;
        }
        
        public String toString() {
            return this.title;
        }
    }    
    
    /**
     * Constructeur
     */
    public ListeEvenement(PlanningUI planning) {
        this.planning = planning;
        
        initListe();        
        initUIComponents();
    }
    
    /**
     * Crée et positionne les composants graphiques constituant l'interface
     */
    private void initUIComponents() {  
        /* 
         * Ajout de la liste de contact dans une zone défilante 
         */
        listeEvenement = new JTree(listeRacine);   
        listeEvenement.setCellRenderer(new MyTreeCellRenderer());
        listeEvenement.addTreeSelectionListener(new TreeSelectionListener() {
            /**
             * Traite les évènements liés à une sélection dans la liste de contacts
             */
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                Object node, data;
                TreePath newSelected = listeEvenement.getSelectionPath();

                /* Vérifie qu'il y a une sélection */
                if (newSelected == null) { return; }
        
                /* Récupère la sélection */
                node = newSelected.getLastPathComponent();
                data = ((DefaultMutableTreeNode) node).getUserObject();
        
                /* Vérifie s'il s'agit d'un contact ou un groupe de contact */
                if (data instanceof NodeData) {
                    selected = newSelected;
                    if (((NodeData) data).id instanceof Contact) {                
                        /* Affiche le contact sélectionné */
                        planning.setSelectedItem(((NodeData) data).id);
                    } else if (((NodeData) data).id instanceof GroupeContacts) {
                        /* Affiche le groupe de contacts sélectionné */
                        planning.setSelectedItem(((NodeData) data).id);
                    }
                } else {
                    listeEvenement.setSelectionPath(selected);
                }
            }
        });

        /* Astuce pour ne pas pouvoir fermer un "dossier" de l'arbre */
        BasicTreeUI treeUI = (BasicTreeUI) listeEvenement.getUI();
        treeUI.setCollapsedIcon(null);
        treeUI.setExpandedIcon(null);

        listeDefilante = new JScrollPane();
//	listeDefilante.getViewport().add(listeDefilante);
        
        setLayout(new BorderLayout());
        add(listeDefilante, BorderLayout.CENTER);        
    }

    /**
     * Crée et initialise la structure arborescente pour le contenu du carnet
     */
    private void initListe() {        
        /* Noeuds élémentaires de l'arbre de contacts */
        listeRacine = new DefaultMutableTreeNode("Planning");
        listeNoeudEvenement = new DefaultMutableTreeNode("Evenement");
        listeRacine.add(listeNoeudEvenement);        
    }

  

    /**
     * Retourne le contact sélectionné
     */
    
     public Evenement getSelectedEvenement() {
        NodeData data;
        Evenement e = null; 
        DefaultMutableTreeNode node;
        
        node = (DefaultMutableTreeNode) selected.getLastPathComponent();
        data = (NodeData) node.getUserObject();
        if (data.id instanceof Contact) {
            e = (Evenement) data.id;
        }

        return e;
    }

   
    
       public boolean ajouterEvenement(Evenement e) {
        boolean res;
        
        if (e == null) { return false; }
        
        res = ajouter(listeNoeudEvenement,e.getIntitule(),e);
        if (res) { selectFirstEvenement(); }
        
        return res;
    }
    
    /**
     * Retire une entrée dans l'arbre pour les groupe
     * @param GroupeContacts groupe que l'on veut retirer
     */    
    public boolean retirerEvenement(Evenement e ) {
        boolean res;
        
        res = retirer(listeNoeudEvenement, e);
        if (res) { selectFirstEvenement(); }
        
        return res;
    }
    
   
    
    
    

    /**
     * Affiche toutes les feuilles de l'arbre du composant listeContacts
     */
    public void showAll() {        
        for(int i = listeEvenement.getRowCount(); i >= 0; i--) {
          listeEvenement.expandRow(i);
        }
    }
    
    public void updateEntry(Object evt) {
        NodeData data;
        Enumeration e;
        DefaultMutableTreeNode node;
        
        if (evt == null) { return; }
        
        if (evt instanceof Evenement) {
           e = listeNoeudEvenement.children();
        } else {
           return;
        }
        
        while(e.hasMoreElements()) {
            node = (DefaultMutableTreeNode) e.nextElement();
            data = (NodeData) node.getUserObject();
            if (evt == data.id ) {
                DefaultTreeModel model;
                if (evt instanceof Evenement) {
                    data.title = ((Evenement) evt).getIntitule() + " " + ((Evenement) evt).getDateJour();
                } else {
                   return;                  
                }
                
                model = (DefaultTreeModel)listeEvenement.getModel();
                model.reload(listeRacine);
                showAll();
                selected = new TreePath(model.getPathToRoot(node));
                listeEvenement.setSelectionPath(selected);
                break;
            }
        }
        
    }
    


    
    private void selectFirstEvenement() {
        NodeData data;        
        Object selectedItem = null;                
        DefaultMutableTreeNode node;
        
        if (! listeNoeudEvenement.isLeaf()) {
           node = (DefaultMutableTreeNode) listeNoeudEvenement.getChildAt(0);
           data = (NodeData) node.getUserObject();        
           selectedItem = data.id;
           highlightSelected(node);           
        }
        
        planning.setSelectedItem(selectedItem);
    }
    
    
    /**
     * Rend visible la sélection
     */
    private void highlightSelected(DefaultMutableTreeNode node) {
        DefaultTreeModel model;
        
        model = (DefaultTreeModel)listeEvenement.getModel();
        selected = new TreePath(model.getPathToRoot(node));
        listeEvenement.setSelectionPath(selected);
    }

    /**
     * Ajoute une entrée dans l'arbre pour un noeud
     * @param title texte affiché dans l'arbre pour un contact
     * @param itemData données associées à l'entrée
     */
    private boolean ajouter(DefaultMutableTreeNode root, String title, Object itemData) {
        boolean success = false;
        
        if ((title != null) && (itemData != null)) {
            DefaultMutableTreeNode node;
        
            node = new DefaultMutableTreeNode(new NodeData(title, itemData));
            // XXX S'occuper du tri par ordre alphabétique
            //root.add(node);
            root.insert(node, 0);
            
            /* redraw content */
            DefaultTreeModel model = (DefaultTreeModel)listeEvenement.getModel();
            model.reload(listeRacine);
            showAll();            
            
            success = true;
        }
        
        return success;
    }
    
    /**
     * Retire une entrée dans l'arbre pour un noeud
     * @param itemData données associées à l'entrée
     */    
    private boolean retirer(DefaultMutableTreeNode root, Object itemData) {
        boolean success = false;
        
        if (itemData != null) {
            NodeData data;
            DefaultMutableTreeNode node;
            int count = root.getChildCount();
            
            for(int i = count - 1; i >= 0; i--) {
                node = (DefaultMutableTreeNode) root.getChildAt(i);
                data = (NodeData) node.getUserObject();
                
                if (itemData.equals(data.id)) {
                    root.remove(i);
                }
            }
            
            /* redraw content */
            DefaultTreeModel model = (DefaultTreeModel)listeEvenement.getModel();
            model.reload(listeRacine);
            showAll();

            success = true;
        }
        
        return success;
    }
}
