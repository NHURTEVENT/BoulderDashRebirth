/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.rassilon.bouderdashrebirth.model.map;

import gal.rassilon.bouderdashrebirth.contracts.iElement;
import gal.rassilon.bouderdashrebirth.model.map.element.blocks.immovable.Air;
import gal.rassilon.bouderdashrebirth.model.map.element.blocks.immovable.Wall;

/**
 *
 * @author Nico
 */
public class MapElementFactory {
    
    public static iElement createElement(char elementChar){
        switch(elementChar){
            case '|':
                return new Wall();
            default :
                return new Air();
        }
    }
}
