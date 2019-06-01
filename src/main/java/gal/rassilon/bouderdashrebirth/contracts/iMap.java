/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.rassilon.bouderdashrebirth.contracts;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JLabel;

/**
 *
 * @author Nico
 */
public interface iMap {
    ArrayList<iElement> getElements();
    ArrayList<iCharacter> getMobs();
    ArrayList<iMovable> getBoulders();
    iCharacter getPlayer();
    Dimension getSize();
    int getLevel();
    iElement[][] getMap();
}
