/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.rassilon.bouderdashrebirth.contracts;

import java.awt.event.KeyEvent;

/**
 *
 * @author Nico
 */
public interface iCharacter extends iElement{
    void move(Direction direction);
    void die();
}
