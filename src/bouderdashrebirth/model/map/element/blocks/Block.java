/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.rassilon.bouderdashrebirth.model.map.element.blocks;

import gal.rassilon.bouderdashrebirth.contracts.State;
import gal.rassilon.bouderdashrebirth.contracts.iBlock;
import gal.rassilon.bouderdashrebirth.model.map.elements.Element;
import java.awt.Point;

/**
 *
 * @author Nico
 */
public abstract class Block extends Element implements iBlock{
    State state;
    boolean destroyable;

    @Override
    public State getState() {
        return state;
    }

    @Override
    public boolean isDestroyable() {
        return destroyable;
    }

    @Override
    public void destroy() {
        //loop sprites
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   
}
