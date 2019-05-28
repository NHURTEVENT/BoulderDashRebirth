/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.rassilon.bouderdashrebirth.contracts;

import java.awt.Dimension;
import java.util.ArrayList;

/**
 *
 * @author Nico
 */
public interface iDAO {
    iElement[][] getMap();
    ArrayList<iElement> getMapList();
    ArrayList<iBlock> getBlocksList();
    ArrayList<iCharacter> getCharactersList();
    int getLevel();
    Dimension getSize();
}
