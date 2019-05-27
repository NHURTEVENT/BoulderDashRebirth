/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.rassilon.bouderdashrebirth.model.map.elements;

import gal.rassilon.bouderdashrebirth.contracts.Direction;
import gal.rassilon.bouderdashrebirth.contracts.Sprite;
import gal.rassilon.bouderdashrebirth.contracts.iElement;
import java.awt.Point;

/**
 *
 * @author Nico
 */
public abstract class Element implements iElement{
    Point position;
    Sprite sprite;

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public Sprite getSprite(Direction direction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}