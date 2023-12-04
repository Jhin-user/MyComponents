package BUS;

import GUI.Window;
import GUISupport.FeatureItem;
import Images.ImageSupport;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 *
 * @author Jhin
 */
public class BUSFeature {

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
        for (FeatureItem f : window.getFeature()) {
            f.addMouseListener(dragWindows);
            f.addMouseMotionListener(dragWindowsMotion);
            f.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    switch (f.getIndex()) {
                        case 0 -> {
                            System.out.println("[BUSFeature]: Click Home");
                            window.getCocLayout().show(window.getCenterOfCenter(), "Home");
                        }
                        case 1 -> {
                            System.out.println("[BUSFeature]: Click Add");
                            window.getCocLayout().show(window.getCenterOfCenter(), "Add");
                        }
                        case 2 -> {
                            System.out.println("[BUSFeature]: Click " + f.getIndex());
                        }
                        case 3 -> {
                            System.out.println("[BUSFeature]: Click " + f.getIndex());
                        }
                        default ->
                            throw new AssertionError();
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    new Thread() {
                        @Override
                        @SuppressWarnings("SleepWhileInLoop")
                        public void run() {
                            for (int i = 31; i <= 40; i++) {
                                f.setIcon(ImageSupport.getSizedIcon(window.getFeatureIcon()[f.getIndex()], i, i));
                                f.setBackground(new Color(200, 200, 200, 100));
                                f.setOpaque(true);
                                window.repaint();
                                try {
                                    Thread.sleep(5);
                                } catch (InterruptedException e) {
                                }
                            }
                        }
                    }.start();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    new Thread() {
                        @Override
                        @SuppressWarnings("SleepWhileInLoop")
                        public void run() {
                            for (int i = 39; i >= 30; i--) {
                                f.setIcon(ImageSupport.getSizedIcon(window.getFeatureIcon()[f.getIndex()], i, i));
                                f.setBackground(new Color(0, 0, 0, 0));
                                f.setOpaque(false);
                                window.repaint();
                                try {
                                    Thread.sleep(5);
                                } catch (InterruptedException e) {
                                }
                            }
                        }
                    }.start();
                }
            });
        }
    }

}
