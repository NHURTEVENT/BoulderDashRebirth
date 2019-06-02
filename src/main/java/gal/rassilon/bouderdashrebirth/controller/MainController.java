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
import gal.rassilon.bouderdashrebirth.model.map.element.blocks.immovable.Dirt;
import gal.rassilon.bouderdashrebirth.model.map.element.blocks.immovable.Star;
import gal.rassilon.bouderdashrebirth.model.map.element.blocks.movable.Boulder;
import gal.rassilon.bouderdashrebirth.model.map.element.blocks.movable.Diamond;
import gal.rassilon.bouderdashrebirth.model.map.element.blocks.movable.MovableBlock;
import gal.rassilon.bouderdashrebirth.model.map.element.characters.Mob;
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
    boolean gameOver;

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
        for (iMovable m : map.getBoulders()) {
            iElement eDown = map.getMap()[m.getPosition().y + 1][m.getPosition().x];
            iElement eRight = map.getMap()[m.getPosition().y][m.getPosition().x + 1];
            iElement eDownRight = map.getMap()[m.getPosition().y + 1][m.getPosition().x + 1];
            iElement eLeft = map.getMap()[m.getPosition().y][m.getPosition().x - 1];
            iElement eDownLeft = map.getMap()[m.getPosition().y + 1][m.getPosition().x - 1];
            if (eDown instanceof Air || eDown instanceof iCharacter) {
                if (!m.isLocked()) {
                    moveElement(Direction.DOWN, m);
                }
                m.setLocked(false);
            } else if (eDown instanceof Boulder) {
                if ((eDownRight instanceof Air || eDownRight instanceof iCharacter) && (eRight instanceof Air || eRight instanceof iCharacter)) {
                    if (!m.isLocked()) {
                        moveElement(Direction.RIGHT, m);
                    }
                    m.setLocked(false);
                } else if ((eDownLeft instanceof Air || eDownLeft instanceof iCharacter) && (eLeft instanceof Air || eLeft instanceof iCharacter)) {
                    if (!m.isLocked()) {
                        moveElement(Direction.LEFT, m);
                    }
                    m.setLocked(false);
                }  else {
                    m.setLocked(true);
                }
            } else {
                m.setLocked(true);
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
            if(!gameOver){
            moveElement((Direction) arg, map.getPlayer());
            //moveElement((Direction) arg, map.getMobs().get(0));
            //moveElement((Direction) arg, map.getBoulders().get(0));
            applyGravity();}
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
                        gameOver = true;
                    }
                } else {
                    if (outElement instanceof iPlayer) {
                        System.out.println("gameover crushed");
                        gameOver = true;
                    } else {
                        view.doMobDeathAnimation(outElement);
                        iElement diamond = new Diamond();
                        diamond.setPosition(outElement.getPosition());
                        diamond.setLevel(map.getLevel());
                        map.getMobs().remove(outElement);
                        map.getBoulders().add((iMovable)diamond);
                        outElement = diamond;
                        map.getMap()[outElement.getPosition().y][outElement.getPosition().x] = diamond;
                        view.getLabels()[outElement.getPosition().y][outElement.getPosition().x].setIcon(diamond.getSprite().getStandingIcon());
                        System.out.println("mob dies");
                        System.out.println("convert to diamond");
                        map.getPlayer().setScore((map.getPlayer().getScore()+100));
                        view.setScore(map.getPlayer().getScore());
                        System.out.println("label unavailable so here's your score :"+((iPlayer)inElement).getScore());
                        return false;
                    }
                }
            }
        }

        if (inElement instanceof MovableBlock) {
            //air, char
            return (outElement instanceof Air || outElement instanceof iCharacter);

        } else if (inElement instanceof iCharacter) {
            if (inElement instanceof iPlayer) {
                if(outElement instanceof Diamond){
                    ((iPlayer) inElement).setDiamondCount(((iPlayer)inElement).getDiamondCount()+1);
                    ((iPlayer) inElement).setScore(((iPlayer)inElement).getScore()+100);
                    view.setScore(((iPlayer)inElement).getScore());
                    System.out.println("label unavailable so here's your score :"+((iPlayer)inElement).getScore());
                }
                //dirt, air, diamond
                return (outElement instanceof Air || outElement instanceof Diamond || outElement instanceof Dirt);

            } else {
                return (outElement instanceof Air || outElement instanceof iCharacter);

                //air,  character 
            }
        } else {
            System.out.println(inElement.toString() + "just moved and shouldn't have");
            return outElement.isTraversable();
        }
    }

    private void doMobDeathAnimation(iElement e){
        if(e instanceof Mob){
            SwingWorker<Void,Void> worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    Star star = new Star();
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            //view.getLabels()[e.getPosition().y - 1][e.getPosition().x-1].setIcon(star.getSprite().getStandingIcon());
                            
                            //map.getMap()[e.getPosition().y-1][e.getPosition().x-1] = star;

                        }
                    }
                    return null;
                }

                @Override
                protected void done() {
                    super.done(); //To change body of generated methods, choose Tools | Templates.
                }
                
                
            };
            worker.run();
        } else {
            System.out.println("we tried to kill "+e.toString()+" and it shouldn't have happenned");
        }
    }
    
    public void moveElement(Direction direction, iElement element) {
        /*SwingWorker<Boolean, Boolean> worker = new SwingWorker<Boolean, Boolean>() {
            @Override
            protected Boolean doInBackground() throws Exception {*/
        Boolean b = SwingUtilities.isEventDispatchThread();
        iElement air = new Air(map.getLevel());
        Point p = element.getPosition();
        ImageIcon icon = (ImageIcon) view.getLabels()[p.y][p.x].getIcon();
        iElement outElement = null;
        switch (direction) {
            case UP:
                outElement = map.getMap()[element.getPosition().y - 1][element.getPosition().x];// = air;
                if (canGo(element, outElement)) {
                    map.getMap()[element.getPosition().y][element.getPosition().x] = air;
                    outElement = air;
                    element.setPosition(new Point(element.getPosition().x, element.getPosition().y - 1));
                    map.getMap()[element.getPosition().y][element.getPosition().x] = element;

                }
                break;

            case DOWN:
                outElement = map.getMap()[element.getPosition().y + 1][element.getPosition().x];
                if (canGo(element, outElement)) {
                    map.getMap()[element.getPosition().y][element.getPosition().x] = air;
                    element.setPosition(new Point(element.getPosition().x, element.getPosition().y + 1));
                    outElement = air;
                    map.getMap()[element.getPosition().y][element.getPosition().x] = element;

                }
                break;
            case RIGHT:
                outElement = map.getMap()[element.getPosition().y][element.getPosition().x + 1];
                if (canGo(element, outElement)) {
                    map.getMap()[element.getPosition().y][element.getPosition().x] = air;
                    element.setPosition(new Point(element.getPosition().x + 1, element.getPosition().y));
                    outElement = air;
                    map.getMap()[element.getPosition().y][element.getPosition().x] = element;

                }
                break;
            case LEFT:
                outElement = map.getMap()[element.getPosition().y][element.getPosition().x - 1];
                if (canGo(element, outElement)) {
                    map.getMap()[element.getPosition().y][element.getPosition().x] = air;
                    element.setPosition(new Point(element.getPosition().x - 1, element.getPosition().y));
                    outElement = air;
                    map.getMap()[element.getPosition().y][element.getPosition().x] = element;

                }
                break;
            case STAND:
                outElement = map.getMap()[element.getPosition().y][element.getPosition().x];
                break;
        }
        //colide(element, outElement);
        //TODO comment if animations are activated
        //map.getMap()[element.getPosition().y][element.getPosition().x] = element;
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
 /*
                return true;
            }
        };
        worker.execute();
        

        return true;*/
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        view.doAnimation();

    }
}
