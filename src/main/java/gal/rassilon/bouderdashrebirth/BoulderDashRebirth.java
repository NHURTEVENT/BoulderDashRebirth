/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.rassilon.bouderdashrebirth;

import gal.rassilon.bouderdashrebirth.contracts.iController;
import gal.rassilon.bouderdashrebirth.contracts.iMap;
import gal.rassilon.bouderdashrebirth.contracts.iView;
import gal.rassilon.bouderdashrebirth.controller.MainController;
import gal.rassilon.bouderdashrebirth.model.map.Map;
import gal.rassilon.bouderdashrebirth.view.MapView;

/**
 *
 * @author Nico
 */
public class BoulderDashRebirth {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        iMap map = new Map();
        MapView view = new MapView(map);
        iController controller = new MainController(map, view);
        controller.start();
    }
    
}
