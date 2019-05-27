/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.rassilon.bouderdashrebirth.model.map.element.blocks.movable;

import gal.rassilon.bouderdashrebirth.contracts.Direction;
import gal.rassilon.bouderdashrebirth.contracts.Sprite;
import gal.rassilon.bouderdashrebirth.contracts.State;
import gal.rassilon.bouderdashrebirth.contracts.iMovable;
import gal.rassilon.bouderdashrebirth.model.map.element.blocks.Block;
import java.awt.Point;

/**
 *
 * @author Nico
 */
public  class MovableBlock extends Block implements iMovable{
    @Override
    public void move(Direction direction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
