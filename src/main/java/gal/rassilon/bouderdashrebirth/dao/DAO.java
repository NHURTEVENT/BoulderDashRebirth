/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.rassilon.bouderdashrebirth.dao;

import gal.rassilon.bouderdashrebirth.contracts.iBlock;
import gal.rassilon.bouderdashrebirth.contracts.iCharacter;
import gal.rassilon.bouderdashrebirth.contracts.iDAO;
import gal.rassilon.bouderdashrebirth.contracts.iElement;
import gal.rassilon.bouderdashrebirth.contracts.iMovable;
import gal.rassilon.bouderdashrebirth.contracts.iPlayer;
import gal.rassilon.bouderdashrebirth.model.map.MapElementFactory;
import gal.rassilon.bouderdashrebirth.model.map.element.blocks.movable.Boulder;
import java.awt.Dimension;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nico
 */
public class DAO implements iDAO {

    iElement[][] map;
    iPlayer player;
    ArrayList<iBlock> blocksList;
    ArrayList<iMovable> boulders;
    ArrayList<iElement> mapList;
    ArrayList<iCharacter> charactersList;
    Dimension size;
    int level;

    @Override
    public Dimension getSize() {
        return size;
    }

    @Override
    public int getLevel() {
        return level;
    }

    public iPlayer getPlayer() {
        return player;
    }

    @Override
    public iElement[][] getMap() {
        load();
        return map;
    }

    @Override
    public ArrayList<iElement> getMapList() {
        return mapList;
    }

    @Override
    public ArrayList<iBlock> getBlocksList() {
        return blocksList;
    }

    @Override
    public ArrayList<iCharacter> getCharactersList() {
        return charactersList;
    }
    
    @Override
    public ArrayList<iMovable> getBouldersList(){
        return boulders;
    }

    public void load() {
        int width = 0;
        int height = 0;
        mapList = new ArrayList<>();
        charactersList = new ArrayList<>();
        blocksList = new ArrayList<>();
        boulders = new ArrayList<>();
        try {
            //read file
            InputStream in = new FileInputStream("Level1.txt");
            Reader r = new InputStreamReader(in);
            int intch;
            int row = -1;
            int readRow = 0;
            int line = 0;
            String value = "";
            while ((intch = r.read()) != -1) {
                char ch = (char) intch;
                //System.out.print(ch);
                if (readRow <= 2) {
                    if (ch == '\r') {
                        int number = Integer.valueOf(value);
                        value = "";
                        switch (readRow) {
                            case 0:
                                height = number;
                                break;
                            case 1:
                                width = number;
                                map = new iElement[height][width];
                                size = new Dimension(width, height);
                                break;
                            case 2:
                                level = number;
                                break;
                        }
                        readRow++;
                    } else if (ch != '\n'){
                        value += ch;
                    }
                } else {
                    if (ch == '\n') {
                        line = -1;
                        row++;
                        //System.out.println("new line");
                        //continue;
                    } else if(ch !='\r'){
                        //System.out.println("r "+row+" l "+line+" "+ch);
                        iElement element = MapElementFactory.createElement(ch);
                        element.setLevel(level);
                        element.setPosition(new Point(line, row));
                        map[row][line] = element;
                        mapList.add(element);
                        if (element instanceof iCharacter) {
                            if(element instanceof iPlayer){
                                player = (iPlayer)element;
                            } else {
                                charactersList.add((iCharacter) element);
                            }
                        } else if (element instanceof iBlock) {
                            if(element instanceof Boulder){
                                boulders.add((iMovable) element);
                            }
                            blocksList.add((iBlock) element);
                        }

                    }
                }
                line++;

            }

            //line 1 height
            //line 2 width
            //line 3 level
            //while lines
            //for(width) passer chaque char à la factory
            //stock l'element dans le tableau, l'add a l'arraylist
            //si ichar add a char sinon a element
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
