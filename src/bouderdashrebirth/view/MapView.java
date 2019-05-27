/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.rassilon.bouderdashrebirth.view;

import gal.rassilon.bouderdashrebirth.contracts.iMap;
import gal.rassilon.bouderdashrebirth.contracts.iView;

/**
 *
 * @author Nico
 */
public class MapView implements iView{
    iMap map;

    public MapView(iMap map) {
        this.map = map;
    }
    
    
}
