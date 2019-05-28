/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.rassilon.bouderdashrebirth.model.map.element.characters;

import gal.rassilon.bouderdashrebirth.contracts.Direction;
import gal.rassilon.bouderdashrebirth.contracts.Sprite;
import gal.rassilon.bouderdashrebirth.model.map.element.blocks.immovable.Wall;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Nico
 */
public class Amoeba extends Mob{

    public Amoeba() {
    HashMap<Direction, ArrayList<Icon>> map = new HashMap<>();
        ArrayList<Icon> images = new ArrayList<>();
        BufferedImage source;
        try {
            source = ImageIO.read(new File("pics/spritesheet.png"));

            Icon icon = new ImageIcon("pics/spritesheet.png");
            icon = new ImageIcon(source.getSubimage(7*16, 0, 16, 16));
            images.add(icon);
            Icon icon2 = new ImageIcon(source.getSubimage(7*16, 1*16, 16, 16));
            images.add(icon2);
            Icon icon3 = new ImageIcon(source.getSubimage(7*16, 2*16, 16, 16));
            images.add(icon3);
            Icon icon4 = new ImageIcon(source.getSubimage(7*16, 3*16, 16, 16));
            images.add(icon4);
            map.put(Direction.STAND, images);
            ArrayList<Icon> destructionAnimation = new ArrayList<>();
            destructionAnimation.add(icon);
            setSprite(new Sprite('A', map, destructionAnimation));
            //this.sprite = 
        } catch (IOException ex) {
            Logger.getLogger(Wall.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
