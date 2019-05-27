/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.rassilon.bouderdashrebirth.model.map;

import gal.rassilon.bouderdashrebirth.contracts.iCharacter;
import gal.rassilon.bouderdashrebirth.contracts.iElement;
import gal.rassilon.bouderdashrebirth.contracts.iMap;
import java.util.ArrayList;

/**
 *
 * @author Nico
 */
public class Map implements iMap{

    @Override
    public ArrayList<iElement> getElements() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<iCharacter> getMobs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public iCharacter getPlayer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
