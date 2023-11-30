package BUS;

import GUI.Window;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Jhin
 */
public class BUSControlWindows {

    private Window window;

    public BUSControlWindows(Window window) {
        this.window = window;
        events();
    }

    private void events() {
        window.getClose().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("[BUSControlWindows]: Close");
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                window.getClose().setOpaque(true);
                window.getClose().setBackground(new Color(255, 0, 0, 150));
                window.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                window.getClose().setOpaque(false);
                window.getClose().setBackground(new Color(0, 0, 0, 0));
                window.repaint();
            }
        });

        window.getExtend().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (window.getWindowsState() == 0) {
                    System.out.println("[BUSControlWindows]: Extend");
                    window.setSize(window.getFullScreenSize());
                    window.setCurrentLocation(window.getLocation());
                    window.setLocation(0, 0);
                    window.setWindowsState(1);
                    window.getBackgroundPanel().setBounds(0, 0, window.getFullScreenSize().width, window.getFullScreenSize().height);
                    return;
                }
                if (window.getWindowsState() == 1) {
                    System.out.println("[BUSControlWindows]: Mini");
                    window.setSize(window.getCurrentSize());
                    window.setLocation(window.getCurrentLocation());
                    window.setWindowsState(0);
                    window.getBackgroundPanel().setBounds(0, 0, window.getCurrentSize().width, window.getCurrentSize().height);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                window.getExtend().setOpaque(true);
                window.getExtend().setBackground(new Color(200, 200, 200, 120));
                window.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                window.getExtend().setOpaque(false);
                window.getExtend().setBackground(new Color(0, 0, 0, 0));
                window.repaint();
            }
        });

        window.getHide().addMouseListener(new MouseAdapter() {
            @Override
            @SuppressWarnings("static-access")
            public void mouseClicked(MouseEvent e) {
                window.setExtendedState(window.ICONIFIED);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                window.getHide().setOpaque(true);
                window.getHide().setBackground(new Color(200, 200, 200, 120));
                window.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                window.getHide().setOpaque(false);
                window.getHide().setBackground(new Color(0, 0, 0, 0));
                window.repaint();
            }
        });
    }

}
