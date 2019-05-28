/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.rassilon.bouderdashrebirth.model.map.element.characters;

import gal.rassilon.bouderdashrebirth.contracts.Direction;
import gal.rassilon.bouderdashrebirth.contracts.iCharacter;
import gal.rassilon.bouderdashrebirth.model.map.elements.Element;
import java.awt.Point;

/**
 *
 * @author Nico
 */
public abstract class Character extends Element implements iCharacter{

    @Override
    public void move(Direction direction) {
        switch(direction){
            case UP:
                setPosition(new Point(getPosition().x, getPosition().y-1));
                break;
            case DOWN:
                setPosition(new Point(getPosition().x, getPosition().y+1));
                break;
            case RIGHT:
                setPosition(new Point(getPosition().x+1, getPosition().y));
                break;
            case LEFT:
                setPosition(new Point(getPosition().x-1, getPosition().y));
                break;
            case STAND:
                break;
        }
    }
    

    @Override
    public void die() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
