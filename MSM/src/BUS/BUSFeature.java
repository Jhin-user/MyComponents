package BUS;

import GUI.Window;
import Images.ImageSupport;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JLabel;

/**
 *
 * @author Jhin
 */
public class BUSFeature {

    int temp = 0;
    private Window window;
    private int x, y;
    private MouseAdapter dragWindows;
    private MouseMotionAdapter dragWindowsMotion;

    public BUSFeature(Window window) {
        this.window = window;
        this.dragWindows = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                window.setCurrentLocation(new Point(window.getX(), window.getY()));
                window.repaint();
            }
        };
        this.dragWindowsMotion = new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                window.setLocation(window.getLocation().x + e.getX() - x, window.getLocation().y + e.getY() - y);
            }
        };
        events();
    }

    private void events() {
        /* -------------------- Drag event -------------------- */
        window.getFeaturePanel().addMouseListener(dragWindows);
        window.getFeaturePanel().addMouseMotionListener(dragWindowsMotion);

        /* -------------------- Feature events -------------------- */
        for (JLabel feature : window.getFeature()) {
//            feature.addMouseListener(dragWindows);
//            feature.addMouseMotionListener(dragWindowsMotion);
            feature.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    switch (feature.getName()) {
                        case "Home" -> {
                            System.out.println("[BUSFeature]: Home");
                            window.getCardLayout().show(window.getCenterOfCenter(), feature.getName());
                        }
                        case "Add" -> {
                            System.out.println("[BUSFeature]: Add");
                            window.getCardLayout().show(window.getCenterOfCenter(), feature.getName());
                        }
                        default ->
                            throw new AssertionError();
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    switch (feature.getName()) {
                        case "Home" ->
                            new Thread() {
                                @Override
                                @SuppressWarnings("SleepWhileInLoop")
                                public void run() {
                                    for (int i = 31; i <= 40; i++) {
                                        feature.setIcon(ImageSupport.getSizedIcon(window.getFeatureIcon()[0], i, i));
                                        feature.setBackground(new Color(200, 200, 200, 100));
                                        feature.setOpaque(true);
                                        window.repaint();
                                        try {
                                            Thread.sleep(5);
                                        } catch (InterruptedException e) {
                                        }
                                    }
                                }
                            }.start();
                        case "Add" ->
                            new Thread() {
                                @Override
                                @SuppressWarnings("SleepWhileInLoop")
                                public void run() {
                                    for (int i = 31; i <= 40; i++) {
                                        feature.setIcon(ImageSupport.getSizedIcon(window.getFeatureIcon()[1], i, i));
                                        feature.setBackground(new Color(200, 200, 200, 100));
                                        feature.setOpaque(true);
                                        window.repaint();
                                        try {
                                            Thread.sleep(5);
                                        } catch (InterruptedException e) {
                                        }
                                    }
                                }
                            }.start();
                        default ->
                            throw new AssertionError();
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    switch (feature.getName()) {
                        case "Home" ->
                            new Thread() {
                                @Override
                                @SuppressWarnings("SleepWhileInLoop")
                                public void run() {
                                    for (int i = 39; i >= 30; i--) {
                                        feature.setIcon(ImageSupport.getSizedIcon(window.getFeatureIcon()[0], i, i));
                                        feature.setBackground(new Color(0, 0, 0, 0));
                                        feature.setOpaque(false);
                                        window.repaint();
                                        try {
                                            Thread.sleep(5);
                                        } catch (InterruptedException e) {
                                        }
                                    }
                                }
                            }.start();
                        case "Add" ->
                            new Thread() {
                                @Override
                                @SuppressWarnings("SleepWhileInLoop")
                                public void run() {
                                    for (int i = 39; i >= 30; i--) {
                                        feature.setIcon(ImageSupport.getSizedIcon(window.getFeatureIcon()[1], i, i));
                                        feature.setBackground(new Color(0, 0, 0, 0));
                                        feature.setOpaque(false);
                                        window.repaint();
                                        try {
                                            Thread.sleep(5);
                                        } catch (InterruptedException e) {
                                        }
                                    }
                                }
                            }.start();
                        default ->
                            throw new AssertionError();
                    }
                }
            });
        }
    }

}
