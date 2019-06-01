/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.rassilon.bouderdashrebirth.view;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import gal.rassilon.bouderdashrebirth.contracts.Direction;
import gal.rassilon.bouderdashrebirth.contracts.iCharacter;
import gal.rassilon.bouderdashrebirth.contracts.iElement;
import gal.rassilon.bouderdashrebirth.contracts.iMap;
import gal.rassilon.bouderdashrebirth.contracts.iView;
import gal.rassilon.bouderdashrebirth.model.map.element.blocks.immovable.Air;
import gal.rassilon.bouderdashrebirth.model.map.element.blocks.immovable.Wall;
import gal.rassilon.bouderdashrebirth.model.map.element.characters.Player;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;

/**
 *
 * @author Nico
 */
public class MapView extends Observable implements iView, ActionListener, ComponentListener, KeyListener {

    private static int REFRESH_TIMER = 500;
    private static int CLOCK_SPEED = 500;
    iMap map;
    JLabel[][] labelArray;
    JPanel mainPanel;
    JFrame mainFrame;
    Direction direction;

    public MapView(iMap map) {
        this.map = map;
        this.labelArray = new JLabel[map.getSize().height][map.getSize().width];
        mainFrame = new JFrame();
        mainFrame.setSize(new Dimension(map.getSize().width * 15, map.getSize().height * 15));
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(map.getSize().height, map.getSize().width));
        //mainPanel.setLayout(new FlowLayout());
        mainFrame.add(mainPanel);
        mainFrame.addKeyListener(this);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Icon icon = new ImageIcon("pics/spritesheet.png");
        //JLabel label = new JLabel(icon);
        //JLabel label2 = new JLabel("hi");
        //JLabel label3 = new JLabel(new Wall().getSprite().getStandingIcon());
        /*mainPanel.add(label3);
        mainPanel.add(label);
        mainPanel.add(label2);*/
 /*for (iElement[] array : map.getMap()) {
            for (iElement e : array) {
                JLabel label4 = new JLabel(e.getSprite().getStandingIcon());
                mainPanel.add(label4);
            }
        }*/

        for (int i = 0; i < map.getSize().height; i++) {
            for (int j = 0; j < map.getSize().width; j++) {
                iElement e = map.getMap()[i][j];
                JLabel label4 = new JLabel(e.getSprite().getStandingIcon());
                labelArray[i][j] = label4;
                mainPanel.add(label4);
            }
        }
        Timer timer = new Timer(REFRESH_TIMER, this);
        timer.setInitialDelay(0);
        timer.start();
        mainFrame.setVisible(true);

        //mainPanel.add(new JLabel(image))
    }

    public void doAnimation() {
        for (int i = 0; i < map.getSize().height; i++) {
            for (int j = 0; j < map.getSize().width; j++) {
                iElement e = map.getMap()[i][j];
                e.cycleSprite();
                JLabel label4 = labelArray[i][j];
                int h = label4.getHeight();
                int w = label4.getWidth();
                label4.setIcon(e.getSprite().getStandingIcon());
                ImageIcon ic = (ImageIcon) label4.getIcon();
                if (h != 0 && w != 0) {
                    Image img = ic.getImage();
                    Image dimg = img.getScaledInstance(label4.getWidth(), label4.getHeight(), Image.SCALE_SMOOTH);
                    label4.setIcon(new ImageIcon(dimg));
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        doAnimation();
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    @Override
    public void componentResized(ComponentEvent e) {
        for (int i = 0; i < map.getSize().height; i++) {
            for (int j = 0; j < map.getSize().width; j++) {
                //iElement element = map.getMap()[i][j];
                JLabel label4 = labelArray[i][j];
                int h = label4.getHeight();
                int w = label4.getWidth();
                //label4.setIcon(element.getSprite().getStandingIcon());
                ImageIcon ic = (ImageIcon) label4.getIcon();
                if (h != 0 && w != 0) {
                    Image img = ic.getImage();
                    Image dimg = img.getScaledInstance(label4.getWidth(), label4.getHeight(), Image.SCALE_SMOOTH);
                    label4.setIcon(new ImageIcon(dimg));
                }
            }
        }
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("typed " + e.getKeyChar());
        switch (e.getKeyCode()) {
            case KeyEvent.VK_Z:
                direction = Direction.UP;
                break;
            case KeyEvent.VK_S:
                direction = Direction.DOWN;
                break;
            case KeyEvent.VK_D:
                direction = Direction.RIGHT;
                break;
            case KeyEvent.VK_Q:
                direction = Direction.LEFT;
                break;
        }
        setChanged();
        notifyObservers(direction);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                direction = Direction.UP;
                break;
            case KeyEvent.VK_DOWN:
                direction = Direction.DOWN;
                break;
            case KeyEvent.VK_RIGHT:
                direction = Direction.RIGHT;
                break;
            case KeyEvent.VK_LEFT:
                direction = Direction.LEFT;
                break;
        }
        setChanged();
        notifyObservers(direction);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    //@Override
    public ListenableFuture<Void> translate(Point position, ImageIcon icon, Direction direction) {
        //Animation anim = new Animation(element, direction);
        //JLabel label = labelArray[position.y][position.x];
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture future = service.submit(new Runnable() {
            @Override
            public void run(){
            
        //iElement foundElement = map.getMap()[position.y][position.x];
        //element.move(direction);
        boolean b = SwingUtilities.isEventDispatchThread();
        
                int hFactor = 0;
                int vFactor = 0;
                switch (direction) {
                    case UP:
                        hFactor = 0;
                        vFactor = -1;
                        break;
                    case DOWN:
                        hFactor = 0;
                        vFactor = 1;
                        break;
                    case RIGHT:
                        hFactor = 1;
                        vFactor = 0;
                        break;
                    case LEFT:
                        hFactor = -1;
                        vFactor = 0;
                        break;
                }
                int size = icon.getIconHeight();
                for (int i = 0; i < size; i++) {
                    Graphics g = mainPanel.getGraphics();
                    int iconWidth = icon.getIconWidth();
                    int iconHeight = icon.getIconHeight();
                    int x = (int)((mainPanel.getBounds().width)/2.0
                            - (map.getSize().width * iconWidth)/2.0)
                            + iconWidth * position.x + i * hFactor;
                    int y = (int)((mainPanel.getBounds().height)/2.0
                            - (map.getSize().height * iconHeight)/2.0)
                            + iconHeight * position.y + i * vFactor;
                    g.drawImage(icon.getImage(), x, y, null);
                    System.out.println("translate");
            try {
                //Boolean b = SwingUtilities.isEventDispatchThread();
                //System.out.println(b);
                Thread.sleep(CLOCK_SPEED / size);
            } catch (InterruptedException ex) {
                Logger.getLogger(MapView.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
                //labelOut.setIcon(new Player().getSprite().getStandingIcon());
                //mainPanel.setLayout(layout);
                //return null;
            }
        
        
        });
        return future;
    }

    @Override
    public JLabel[][] getLabels(){
        return labelArray;
    }
    
    private class Animation /*extends SwingWorker<Void, Void>*/ {

        iElement element;
        Direction direction;

        public Animation(iElement element, Direction direction) {
            this.element = element;
            this.direction = direction;
        }
        /*
        @Override
        protected Void doInBackground() throws Exception {
            for (int i = 0; i < map.getSize().height; i++) {
                for (int j = 0; j < map.getSize().width; j++) {
                    iElement e = map.getMap()[i][j];
                    e.cycleSprite();
                    JLabel label4 = labelArray[i][j];
                    int h = label4.getHeight();
                    int w = label4.getWidth();
                    label4.setIcon(e.getSprite().getStandingIcon());
                    ImageIcon ic = (ImageIcon) label4.getIcon();
                    if (h != 0 && w != 0) {
                        Image img = ic.getImage();
                        Image dimg = img.getScaledInstance(label4.getWidth(), label4.getHeight(), Image.SCALE_SMOOTH);
                        label4.setIcon(new ImageIcon(dimg));
                    }
                }
            }
            return null;
        }*/

    }
}
