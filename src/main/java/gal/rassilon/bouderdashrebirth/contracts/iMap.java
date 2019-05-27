/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.rassilon.bouderdashrebirth.contracts;

import java.util.ArrayList;

/**
 *
 * @author Nico
 */
public interface iMap {
    ArrayList<iElement> getElements();
    ArrayList<iCharacter> getMobs();
    iCharacter getPlayer();
}
