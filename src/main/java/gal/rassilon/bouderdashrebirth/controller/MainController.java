/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.rassilon.bouderdashrebirth.controller;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import gal.rassilon.bouderdashrebirth.contracts.Direction;
import gal.rassilon.bouderdashrebirth.contracts.iController;
import gal.rassilon.bouderdashrebirth.contracts.iElement;
import gal.rassilon.bouderdashrebirth.contracts.iMap;
import gal.rassilon.bouderdashrebirth.contracts.iView;
import gal.rassilon.bouderdashrebirth.model.map.element.blocks.immovable.Air;
import gal.rassilon.bouderdashrebirth.view.MapView;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Nico
 */
public class MainController implements iController, Runnable, Observer {

    iMap map;
    iView view;

    public MainController(iMap map, MapView view) {
        this.map = map;
        this.view = view;
        view.addObserver(this);

    }

    @Override
    public void updateView() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void applyGravity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doAnimation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void moveMobs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void movePlayer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {
        System.out.println("hi");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void start() {
        run();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Direction) {
            System.out.println(arg.toString());
            Point p = map.getPlayer().getPosition();
            JLabel labelOut = getOutLabel(p, (Direction)arg);
            ListenableFuture<Void> future = view.translate(p, view.getLabels()[p.y][p.x], labelOut, (Direction) arg);
            //ListenableFuture<Integer> f2 = Futures.transformAsync(future, function, executor)
            Futures.addCallback(future, new FutureCallback() {
                @Override
                public void onSuccess(Object v) {
                    moveElement((Direction) arg, map.getPlayer());
                }

                @Override
                public void onFailure(Throwable thrwbl) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        }
    }
    
    private JLabel getOutLabel(Point position, Direction direction){
        switch (direction) {
            case UP:
                //air setposition
                return view.getLabels()[position.y - 1][position.x];
            case DOWN:
                return view.getLabels()[position.y + 1][position.x];
            case RIGHT:
                return view.getLabels()[position.y][position.x + 1];
            case LEFT:
                return view.getLabels()[position.y][position.x - 1];
            case STAND:
                return view.getLabels()[position.y][position.x];
            default :
                return view.getLabels()[position.y][position.x];
        }
    }

    public void moveElement(Direction direction, iElement element) {
        iElement air = new Air(map.getLevel());
        switch (direction) {
            case UP:
                map.getMap()[element.getPosition().y - 1][element.getPosition().x] = element;
                map.getMap()[element.getPosition().y][element.getPosition().x] = air;
                //air setposition
                //view.getLabels()[element.getPosition().y - 1][element.getPosition().x].setIcon(element.getSprite().getStandingIcon());
                //view.getLabels()[element.getPosition().y][element.getPosition().x].setIcon(air.getSprite().getStandingIcon());
                element.setPosition(new Point(element.getPosition().x, element.getPosition().y - 1));
                break;
            case DOWN:
                map.getMap()[element.getPosition().y + 1][element.getPosition().x] = element;
                map.getMap()[element.getPosition().y][element.getPosition().x] = air;
                //air setposition
                //view.getLabels()[element.getPosition().y + 1][element.getPosition().x].setIcon(element.getSprite().getStandingIcon());
                //view.getLabels()[element.getPosition().y][element.getPosition().x].setIcon(air.getSprite().getStandingIcon());
                element.setPosition(new Point(element.getPosition().x, element.getPosition().y + 1));
                break;
            case RIGHT:
                map.getMap()[element.getPosition().y][element.getPosition().x + 1] = element;
                map.getMap()[element.getPosition().y][element.getPosition().x] = new Air(map.getLevel());
                //view.getLabels()[element.getPosition().y][element.getPosition().x + 1].setIcon(element.getSprite().getStandingIcon());
                //view.getLabels()[element.getPosition().y][element.getPosition().x].setIcon(air.getSprite().getStandingIcon());
                //air setposition
                element.setPosition(new Point(element.getPosition().x + 1, element.getPosition().y));
                break;
            case LEFT:
                map.getMap()[element.getPosition().y][element.getPosition().x - 1] = element;
                map.getMap()[element.getPosition().y][element.getPosition().x] = new Air(map.getLevel());
                //view.getLabels()[element.getPosition().y][element.getPosition().x - 1].setIcon(element.getSprite().getStandingIcon());
                //view.getLabels()[element.getPosition().y][element.getPosition().x].setIcon(air.getSprite().getStandingIcon());
                //air setposition
                element.setPosition(new Point(element.getPosition().x - 1, element.getPosition().y));
                break;
            case STAND:
                break;
        }
    }

}
