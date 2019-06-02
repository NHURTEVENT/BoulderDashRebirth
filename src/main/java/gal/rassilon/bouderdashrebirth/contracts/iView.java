/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.rassilon.bouderdashrebirth.contracts;

import com.google.common.util.concurrent.ListenableFuture;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Nico
 */
public interface iView{
    ListenableFuture<Void> translate(Point position, ImageIcon icon, Direction direction);
    JLabel[][] getLabels();
    void doAnimation();
    void doMobDeathAnimation(iElement e);
    void setScore(int score);
}
