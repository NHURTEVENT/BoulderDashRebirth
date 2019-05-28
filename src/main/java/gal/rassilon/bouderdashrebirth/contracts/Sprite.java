/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.rassilon.bouderdashrebirth.contracts;

import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.Icon;

/**
 *
 * @author Nico
 */
public class Sprite {
    char consoleImage;
    HashMap<Direction, ArrayList<Icon>> imageMap;
    ArrayList<Icon> destructionAnimation;
    int cycleNumber;

    public Sprite(char consoleImage, HashMap<Direction, ArrayList<Icon>> imageMap, ArrayList<Icon> destructionAnimation) {
        this.consoleImage = consoleImage;
        this.imageMap = imageMap;
        this.destructionAnimation = destructionAnimation;
    }

    public void CycleSprite(int cycleNumber){
        this.cycleNumber = cycleNumber%8;
    }
    
    public Icon getStandingIcon(){
        return imageMap.get(Direction.STAND).get(cycleNumber);
    }
}
