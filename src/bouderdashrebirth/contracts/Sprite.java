/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.rassilon.bouderdashrebirth.contracts;

import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Nico
 */
public class Sprite {
    char consoleImage;
    HashMap<Direction, ArrayList<Image>> imageMap;
    ArrayList<Image> destructionAnimation;

    public Sprite(char consoleImage, HashMap<Direction, ArrayList<Image>> imageMap, ArrayList<Image> destructionAnimation) {
        this.consoleImage = consoleImage;
        this.imageMap = imageMap;
        this.destructionAnimation = destructionAnimation;
    }


}
