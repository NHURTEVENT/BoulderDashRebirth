/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.rassilon.bouderdashrebirth.contracts;

import java.awt.Point;

/**
 *
 * @author Nico
 */
public interface iElement {
    Point getPosition();
    void setPosition(Point position);
    Sprite getSprite();
}
