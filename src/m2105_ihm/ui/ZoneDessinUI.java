/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.awt.Point;
import java.awt.Color;
import java.awt.Canvas;
import java.awt.Polygon;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author IUT2
 */
/**
 * 
 * @class ZoneDessinUI
 * Zone d'édition d'un logo
 */
public class ZoneDessinUI extends Canvas  {

    /*
     * Liste des points définissant le logo
     */
    private Polygon polygon;
    
    public ZoneDessinUI() {
        super();
                
        polygon = new Polygon();
        
        /*
         * Abonne le canvas aux évènements souris
         */
        addMouseListener(new MouseListener() {
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                polygon.addPoint(e.getX(), e.getY());
                repaint();
            }
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseClicked(MouseEvent e) {}
        });
        
        /** TP 2 : à compléter **/
        //polygon.addPoint(100, 50);
        //polygon.addPoint(50, 50);
        //polygon.addPoint(80, 75);
        //polygon.addPoint(50, 100);
        //polygon.addPoint(100, 100);
    }
    
    /**
     * Dessine le contenu du canvas, c'est-à-dire l'icone
     * @param g un contexte graphique
     */
    @Override
    public void paint(Graphics g) {
        Dimension dim = getSize();

        /** TP 2 : à modifier et compléter **/
        setBackground(Color.WHITE);
        
        g.setColor(Color.BLUE);
        g.drawLine(0, 0, dim.width-1, 0);
        g.drawLine(dim.width-1, 0, dim.width-1, dim.height-1);
        g.drawLine(dim.width-1, dim.height-1, 0, dim.height-1);
        g.drawLine(0, dim.height-1, 0, 0);
        
        g.setColor(Color.RED);
        
        /*for (int i = 0; i < this.polygon.npoints-1; i++)  {
            g.drawLine(this.polygon.xpoints[i], this.polygon.ypoints[i], this.polygon.xpoints[i+1], this.polygon.ypoints[i+1]);
        }*/
        
        g.drawPolygon(polygon);
        
        /*
         * Dessine une diagonale en fonction de la taille du canvas
         */
        //g.drawLine(0, 0, dim.width, dim.height);        
    }

    /**
     * Efface le dessin
     */
    public void effacer() {
        
        /** TP 2 : à compléter **/
        polygon.reset();
        
        this.repaint();
    }
        
    /**
     * Affecte le logo avec un ensemble de points
     * @param points tableau de points
     */
    public void setPoints(Point [] dessin) {
        
        /** TP 2 : à compléter **/
        for (Point p : dessin) {
            polygon.addPoint(p.x,p.y);
        }
        
        this.repaint();
    }

    /**
     * Retourne les points définissant l'icône
     * @return tableau d'entiers
     */
    public Point [] getPoints() {
        Point [] res;
        
        res = new Point[polygon.npoints];
        for(int i = 0; i < res.length; i++) {
            res[i] = new Point(polygon.xpoints[i], polygon.ypoints[i]);
        }
        
        return res;
    }
        
    /*
     * Taille fixe
     */
    @Override
    public Dimension getSize() {
        return new Dimension(150, 150);        
    }          
    @Override
    public Dimension getMinimumSize() {
        return new Dimension(150, 150);        
    }          
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(150, 150);        
    }          
}