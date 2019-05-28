/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.rassilon.bouderdashrebirth.model.map;

import gal.rassilon.bouderdashrebirth.contracts.iCharacter;
import gal.rassilon.bouderdashrebirth.contracts.iDAO;
import gal.rassilon.bouderdashrebirth.contracts.iElement;
import gal.rassilon.bouderdashrebirth.contracts.iMap;
import gal.rassilon.bouderdashrebirth.dao.DAO;
import java.awt.Dimension;
import java.util.ArrayList;

/**
 *
 * @author Nico
 */
public class Map implements iMap{

    Dimension size;
    iElement[][] map;
    ArrayList<iElement> elementsOnTheMap;
    ArrayList<iCharacter> charactersOnTheMap;
    
    

    public Map() {
        iDAO dao = new DAO();
        map = dao.getMap();
        elementsOnTheMap = dao.getMapList();
        this.size = new Dimension(dao.getSize());
        //this.size = new Dimension(500,500);
    }
    
    
    
    @Override
    public ArrayList<iElement> getElements() {
        return elementsOnTheMap;
    }

    @Override
    public ArrayList<iCharacter> getMobs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public iCharacter getPlayer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Dimension getSize() {
        return this.size;
    }
    
    @Override
    public iElement[][] getMap(){
        return map;
    }
}
