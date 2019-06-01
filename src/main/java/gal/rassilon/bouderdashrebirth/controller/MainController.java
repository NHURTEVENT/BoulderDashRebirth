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
import gal.rassilon.bouderdashrebirth.contracts.iCharacter;
import gal.rassilon.bouderdashrebirth.contracts.iController;
import gal.rassilon.bouderdashrebirth.contracts.iElement;
import gal.rassilon.bouderdashrebirth.contracts.iMap;
import gal.rassilon.bouderdashrebirth.contracts.iMovable;
import gal.rassilon.bouderdashrebirth.contracts.iPlayer;
import gal.rassilon.bouderdashrebirth.contracts.iView;
import gal.rassilon.bouderdashrebirth.model.map.element.blocks.immovable.Air;
import gal.rassilon.bouderdashrebirth.view.MapView;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;

/**
 *
 * @author Nico
 */
public class MainController implements iController, Runnable, Observer, ActionListener {

    iMap map;
    iView view;
    private static int REFRESH_TIMER = 500;

    public MainController(iMap map, MapView view) {
        this.map = map;
        this.view = view;
        view.addObserver(this);
        Timer timer = new Timer(REFRESH_TIMER, this);
        timer.setInitialDelay(0);
        timer.start();

    }

    private boolean checkColision(iElement moving, iElement direction) {
        return direction.isTraversable();
    }

    @Override
    public void updateView() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void applyGravity() {
        for(iMovable m : map.getBoulders()){
            iElement e = map.getMap()[m.getPosition().y+1][m.getPosition().x];
            if(e instanceof Air || e instanceof iCharacter){
                moveElement(Direction.DOWN, m);
            }
        }
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
            /*System.out.println(arg.toString());
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
            });*/
            moveElement((Direction) arg, map.getPlayer());
            moveElement((Direction) arg, map.getMobs().get(0));
            moveElement((Direction) arg, map.getBoulders().get(0));
            applyGravity();
        }
    }

    private JLabel getOutLabel(Point position, Direction direction) {
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
            default:
                return view.getLabels()[position.y][position.x];
        }
    }

    private boolean canGo(iElement inElement, iElement outElement) {
        if (outElement.isTraversable()) {
            if (outElement instanceof iCharacter) {

                if (inElement instanceof iCharacter) {
                    if (inElement instanceof iPlayer) {
                        System.out.println("gameover killed by mob");
                    }
                } else {
                    if (outElement instanceof iPlayer) {
                        System.out.println("gameover crushed");
                    } else {
                        System.out.println("mob dies");
                        System.out.println("convert to diamond");
                    }
                }
            }
        }
        return outElement.isTraversable();
    }

    public boolean moveElement(Direction direction, iElement element) {
        SwingWorker<Boolean, Boolean> worker = new SwingWorker<Boolean, Boolean>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                Boolean b = SwingUtilities.isEventDispatchThread();
                iElement air = new Air(map.getLevel());
                Point p = element.getPosition();
                ImageIcon icon = (ImageIcon) view.getLabels()[p.y][p.x].getIcon();
                iElement outElement;
                switch (direction) {
                    case UP:
                        outElement = map.getMap()[element.getPosition().y - 1][element.getPosition().x];// = air;
                        if (canGo(element, outElement)) {
                            map.getMap()[element.getPosition().y][element.getPosition().x] = air;
                            outElement = air;
                            element.setPosition(new Point(element.getPosition().x, element.getPosition().y - 1));
                        }
                        //air setposition
                        //view.getLabels()[element.getPosition().y - 1][element.getPosition().x].setIcon(element.getSprite().getStandingIcon());
                        //view.getLabels()[element.getPosition().y][element.getPosition().x].setIcon(air.getSprite().getStandingIcon());

                        break;

                    case DOWN:
                        outElement = map.getMap()[element.getPosition().y + 1][element.getPosition().x];
                        if (canGo(element, outElement)) {
                            map.getMap()[element.getPosition().y][element.getPosition().x] = air;
                            element.setPosition(new Point(element.getPosition().x, element.getPosition().y + 1));
                            outElement = air;
                        }
                        //air setposition
                        //view.getLabels()[element.getPosition().y + 1][element.getPosition().x].setIcon(element.getSprite().getStandingIcon());
                        //view.getLabels()[element.getPosition().y][element.getPosition().x].setIcon(air.getSprite().getStandingIcon());
                        break;
                    case RIGHT:
                        outElement = map.getMap()[element.getPosition().y][element.getPosition().x + 1];
                        if (canGo(element, outElement)) {
                            map.getMap()[element.getPosition().y][element.getPosition().x] = air;
                            element.setPosition(new Point(element.getPosition().x + 1, element.getPosition().y));
                            outElement = air;
                        }
                        //view.getLabels()[element.getPosition().y][element.getPosition().x + 1].setIcon(element.getSprite().getStandingIcon());
                        //view.getLabels()[element.getPosition().y][element.getPosition().x].setIcon(air.getSprite().getStandingIcon());
                        //air setposition
                        break;
                    case LEFT:
                        outElement = map.getMap()[element.getPosition().y][element.getPosition().x - 1];
                        if (canGo(element, outElement)) {
                            map.getMap()[element.getPosition().y][element.getPosition().x] = air;
                            element.setPosition(new Point(element.getPosition().x - 1, element.getPosition().y));
                            outElement = air;
                        }
                        //view.getLabels()[element.getPosition().y][element.getPosition().x - 1].setIcon(element.getSprite().getStandingIcon());
                        //view.getLabels()[element.getPosition().y][element.getPosition().x].setIcon(air.getSprite().getStandingIcon());
                        //air setposition
                        break;
                    case STAND:
                        break;
                }
                
                
                //TODO comment if animations are activated
                map.getMap()[element.getPosition().y][element.getPosition().x] = element;
                //TODO uncomment to reactivate animations
                /*
                ListenableFuture<Void> future = view.translate(p, icon, direction);
                Futures.addCallback(future,
                        new FutureCallback<Void>() {
                    @Override
                    public void onSuccess(Void v) {
                        map.getMap()[element.getPosition().y][element.getPosition().x] = element;
                    }

                    @Override
                    public void onFailure(Throwable thrwbl
                    ) {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                }
                );*/

                return true;
            }
        };
        worker.execute();

        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        view.doAnimation();

    }
}
