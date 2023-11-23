package BUS;

import GUI.CenterPanelBackground;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 *
 * @author Jhin
 */
public class BUSCenterPanelBackground {

    private CenterPanelBackground centerPanelBackground;

    // Pressed point
    private int x, y;

    public BUSCenterPanelBackground(CenterPanelBackground centerPanelBackground) {
        this.centerPanelBackground = centerPanelBackground;
        titleEvents();
        contentEvents();
    }

    private void titleEvents() {
        centerPanelBackground.getTitlePanel().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }
        });

        centerPanelBackground.getTitlePanel().addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (centerPanelBackground.getMainWindows().getWindowsState() == 0) {
                    centerPanelBackground.getMainWindows().setLocation(centerPanelBackground.getMainWindows().getLocation().x + e.getX() - x, centerPanelBackground.getMainWindows().getLocation().y + e.getY() - y);
                }
            }
        });

        centerPanelBackground.getMinimize().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("[BUSCenterPanel]: Minimize");
                centerPanelBackground.getMainWindows().setExtendedState(javax.swing.JFrame.ICONIFIED);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                centerPanelBackground.getMinimize().setOpaque(true);
                centerPanelBackground.getMinimize().setBackground(new Color(128, 128, 128, 128));
                centerPanelBackground.getControl().revalidate();
                centerPanelBackground.getControl().repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                centerPanelBackground.getMinimize().setOpaque(false);
                centerPanelBackground.getMinimize().setBackground(new Color(128, 128, 128, 128));
                centerPanelBackground.getControl().revalidate();
                centerPanelBackground.getControl().repaint();
            }
        });

        centerPanelBackground.getMaximize().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("[BUSCenterPanel]: Maximize");
                if (centerPanelBackground.getMainWindows().getWindowsState() == 0) {
                    centerPanelBackground.getMainWindows().setSize(centerPanelBackground.getMainWindows().getFullScreenSize());
                    centerPanelBackground.getMainWindows().setLocationRelativeTo(null);
                    centerPanelBackground.getMainWindows().getMainPanel().setBounds(0, 0, centerPanelBackground.getMainWindows().getFullScreenSize().width, centerPanelBackground.getMainWindows().getFullScreenSize().height);
                    centerPanelBackground.getMainWindows().setWindowsState(1);
                    centerPanelBackground.getMaximize().setIcon(centerPanelBackground.getRestoreW());
                } else {
                    centerPanelBackground.getMainWindows().setSize(centerPanelBackground.getMainWindows().getCurrentSize());
                    centerPanelBackground.getMainWindows().setLocationRelativeTo(null);
                    centerPanelBackground.getMainWindows().getMainPanel().setBounds(0, 0, centerPanelBackground.getMainWindows().getCurrentSize().width, centerPanelBackground.getMainWindows().getCurrentSize().height);
                    centerPanelBackground.getMainWindows().setWindowsState(0);
                    centerPanelBackground.getMaximize().setIcon(centerPanelBackground.getMaximizeW());
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                centerPanelBackground.getMaximize().setBackground(new Color(128, 128, 128, 128));
                centerPanelBackground.getMaximize().setOpaque(true);
                centerPanelBackground.getControl().revalidate();
                centerPanelBackground.getControl().repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                centerPanelBackground.getMaximize().setBackground(new Color(128, 128, 128, 128));
                centerPanelBackground.getMaximize().setOpaque(false);
                centerPanelBackground.getControl().revalidate();
                centerPanelBackground.getControl().repaint();
            }
        });

        centerPanelBackground.getClose().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("[BUSCenterPanel]: Close");
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                centerPanelBackground.getClose().setOpaque(true);
                centerPanelBackground.getClose().setBackground(new Color(255, 0, 0, 192));
                centerPanelBackground.getControl().revalidate();
                centerPanelBackground.getControl().repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                centerPanelBackground.getClose().setOpaque(false);
                centerPanelBackground.getClose().setBackground(new Color(255, 0, 0, 192));
                centerPanelBackground.getControl().revalidate();
                centerPanelBackground.getControl().repaint();
            }
        });
    }

    private void contentEvents() {
        
    }

}
