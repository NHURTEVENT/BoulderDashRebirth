/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.rassilon.bouderdashrebirth.model.map.element.blocks.immovable;

import gal.rassilon.bouderdashrebirth.contracts.Direction;
import gal.rassilon.bouderdashrebirth.contracts.Sprite;
import java.awt.Image;
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
public class Wall extends ImmovableBlock{

    public Wall() {
    }
    
        @Override
    public void setLevel(int level){
        super.setLevel(level);
        HashMap<Direction, ArrayList<Icon>> map = new HashMap<>();
        ArrayList<Icon> images = new ArrayList<>();
        BufferedImage source;
        try {
            source = ImageIO.read(new File("pics/spritesheet.png"));

            Icon icon = new ImageIcon("pics/spritesheet.png");
            icon = new ImageIcon(source.getSubimage(0*16, (level-1)*4*16+0*16, 16, 16));
            images.add(icon);
            Icon icon2 = new ImageIcon(source.getSubimage(0*16, (level-1)*4*16+0*16, 16, 16));
            images.add(icon2);
            Icon icon3 = new ImageIcon(source.getSubimage(0*16, (level-1)*4*16+1*16, 16, 16));
            images.add(icon3);
            Icon icon4 = new ImageIcon(source.getSubimage(0*16, (level-1)*4*16+1*16, 16, 16));
            images.add(icon4);
            Icon icon5 = new ImageIcon(source.getSubimage(0*16, (level-1)*4*16+2*16, 16, 16));
            images.add(icon5);
            Icon icon6 = new ImageIcon(source.getSubimage(0*16, (level-1)*4*16+2*16, 16, 16));
            images.add(icon6);
            Icon icon7 = new ImageIcon(source.getSubimage(0*16, (level-1)*4*16+3*16, 16, 16));
            images.add(icon7);
            Icon icon8 = new ImageIcon(source.getSubimage(0*16, (level-1)*4*16+3*16, 16, 16));
            images.add(icon8);
            map.put(Direction.STAND, images);
            ArrayList<Icon> destructionAnimation = new ArrayList<>();
            destructionAnimation.add(icon);
            setSprite(new Sprite('_', map, destructionAnimation));
            //this.sprite = 
        } catch (IOException ex) {
            Logger.getLogger(Wall.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
