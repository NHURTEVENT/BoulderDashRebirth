/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.rassilon.bouderdashrebirth.contracts;

/**
 *
 * @author Nico
 */
public interface iController {
    void updateView();
    void applyGravity();
    void doAnimation();
    void moveMobs();
    void movePlayer();
    void start();
}
