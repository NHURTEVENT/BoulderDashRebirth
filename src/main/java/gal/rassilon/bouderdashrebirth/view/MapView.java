/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.rassilon.bouderdashrebirth.view;

import gal.rassilon.bouderdashrebirth.contracts.Direction;
import gal.rassilon.bouderdashrebirth.contracts.iElement;
import gal.rassilon.bouderdashrebirth.contracts.iMap;
import gal.rassilon.bouderdashrebirth.contracts.iView;
import gal.rassilon.bouderdashrebirth.model.map.element.blocks.immovable.Wall;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Nico
 */
public class MapView extends JFrame implements iView {

    iMap map;

    public MapView(iMap map) {
        this.map = map;
        this.setSize(new Dimension(map.getSize().width*15, map.getSize().height*15));
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(map.getSize().height, map.getSize().width));
        //mainPanel.setLayout(new FlowLayout());
        this.add(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Icon icon = new ImageIcon("pics/spritesheet.png");
        JLabel label = new JLabel(icon);
        JLabel label2 = new JLabel("hi");
        JLabel label3 = new JLabel(new Wall().getSprite().getStandingIcon());
        /*mainPanel.add(label3);
        mainPanel.add(label);
        mainPanel.add(label2);*/
        /*for (iElement[] array : map.getMap()) {
            for (iElement e : array) {
                JLabel label4 = new JLabel(e.getSprite().getStandingIcon());
                mainPanel.add(label4);
            }
        }*/
        
        for (int i=0; i<map.getSize().height;i++) {
            for (int j=0; j<map.getSize().width;j++) {
                iElement e = map.getMap()[i][j];
                JLabel label4 = new JLabel(e.getSprite().getStandingIcon());
                mainPanel.add(label4);
            }
        }
        
        this.setVisible(true);

        //mainPanel.add(new JLabel(image))
    }

}
