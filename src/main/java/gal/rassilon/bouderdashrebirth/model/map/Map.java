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
import gal.rassilon.bouderdashrebirth.model.map.element.characters.Player;
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
    iCharacter player;
    int level;
    
    

    public Map() {
        iDAO dao = new DAO();
        map = dao.getMap();
        level = dao.getLevel();
        charactersOnTheMap = dao.getCharactersList();
        /*for(iCharacter c : charactersOnTheMap){
            if(c instanceof Player){
                this.player = c;
            }
        }*/
        this.player = dao.getPlayer();
        
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
        return charactersOnTheMap;
    }

    @Override
    public iCharacter getPlayer() {
        return player;
    }

    @Override
    public Dimension getSize() {
        return this.size;
    }
    
    @Override
    public iElement[][] getMap(){
        return map;
    }

    @Override
    public int getLevel() {
        return level;
    }
}
