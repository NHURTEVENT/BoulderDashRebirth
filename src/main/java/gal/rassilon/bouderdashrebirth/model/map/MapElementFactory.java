/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.rassilon.bouderdashrebirth.model.map;

import gal.rassilon.bouderdashrebirth.contracts.iElement;
import gal.rassilon.bouderdashrebirth.model.map.element.blocks.immovable.Air;
import gal.rassilon.bouderdashrebirth.model.map.element.blocks.immovable.Dirt;
import gal.rassilon.bouderdashrebirth.model.map.element.blocks.immovable.ExitDoor;
import gal.rassilon.bouderdashrebirth.model.map.element.blocks.immovable.Wall;
import gal.rassilon.bouderdashrebirth.model.map.element.blocks.movable.Boulder;
import gal.rassilon.bouderdashrebirth.model.map.element.blocks.movable.Diamond;
import gal.rassilon.bouderdashrebirth.model.map.element.characters.Lefty;
import gal.rassilon.bouderdashrebirth.model.map.element.characters.Player;
import gal.rassilon.bouderdashrebirth.model.map.element.characters.Righty;

/**
 *
 * @author Nico
 */
public class MapElementFactory {

    public static iElement createElement(char elementChar) {
        switch (elementChar) {
            case '|':
                return new Wall();
            case 'M':
                return new Dirt();
            case 'D':
                return new Diamond();
            case '_':
                return new Air();
            case 'O':
                return new Boulder();
            case 'L':
                return new Lefty();
            case 'R':
                return new Righty();
            case 'H':
                return new Player();
            case 'E':
                return new ExitDoor();
            default:
                return new Lefty();
        }
    }
}
