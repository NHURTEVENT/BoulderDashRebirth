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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Nico
 */
public class MapView extends JFrame implements iView, ActionListener {

    iMap map;
    JLabel[][] labelArray;
    JPanel mainPanel;

    public MapView(iMap map) {
        this.map = map;
        this.labelArray = new JLabel[map.getSize().height][map.getSize().width];
        this.setSize(new Dimension(map.getSize().width*15, map.getSize().height*15));
        mainPanel = new JPanel();
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
                labelArray[i][j] = label4;
                mainPanel.add(label4);
            }
        }
        Timer timer = new Timer(500,this);
        timer.setInitialDelay(0);
        timer.start();
        this.setVisible(true);

        //mainPanel.add(new JLabel(image))
    }

    public void doAnimation(){
        for (int i=0; i<map.getSize().height;i++) {
            for (int j=0; j<map.getSize().width;j++) {
                iElement e = map.getMap()[i][j];
                e.cycleSprite();
                JLabel label4 = labelArray[i][j];
                label4.setIcon(e.getSprite().getStandingIcon());
                Image img = (Image) label4.getIcon();
                Image dimg = img.getScaledInstance(label4.getWidth(), label4.getHeight(),Image.SCALE_SMOOTH);
                label4.setIcon(new ImageIcon(dimg));
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        doAnimation();
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
