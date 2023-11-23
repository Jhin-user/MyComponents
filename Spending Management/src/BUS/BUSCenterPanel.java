package BUS;

import GUI.CenterPanel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 *
 * @author Jhin
 */
public class BUSCenterPanel {

    private CenterPanel centerPanel;

    // Pressed point
    private int x, y;

    public BUSCenterPanel(CenterPanel centerPanel) {
        this.centerPanel = centerPanel;
        titleEvents();
        contentEvents();
    }

    private void titleEvents() {
        centerPanel.getTitlePanel().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }
        });

        centerPanel.getTitlePanel().addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (centerPanel.getMainWindows().getWindowsState() == 0) {
                    centerPanel.getMainWindows().setLocation(centerPanel.getMainWindows().getLocation().x + e.getX() - x, centerPanel.getMainWindows().getLocation().y + e.getY() - y);
                }
            }
        });

        centerPanel.getMinimize().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("[BUSCenterPanel]: Minimize");
                centerPanel.getMainWindows().setExtendedState(javax.swing.JFrame.ICONIFIED);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                centerPanel.getMinimize().setIcon(centerPanel.getMiniW());
                centerPanel.getMinimize().setBackground(Color.gray);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                centerPanel.getMinimize().setIcon(centerPanel.getMiniB());
                centerPanel.getMinimize().setBackground(Color.white);
            }
        });

        centerPanel.getMaximize().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("[BUSCenterPanel]: Maximize");
                if (centerPanel.getMainWindows().getWindowsState() == 0) {
                    centerPanel.getMainWindows().setSize(centerPanel.getMainWindows().getFullScreenSize());
                    centerPanel.getMainWindows().setLocationRelativeTo(null);
                    centerPanel.getMainWindows().getMainPanel().setBounds(0, 0, centerPanel.getMainWindows().getFullScreenSize().width, centerPanel.getMainWindows().getFullScreenSize().height);
                    centerPanel.getMainWindows().setWindowsState(1);
                    centerPanel.getMaximize().setIcon(centerPanel.getRestoreB());
                } else {
                    centerPanel.getMainWindows().setSize(centerPanel.getMainWindows().getCurrentSize());
                    centerPanel.getMainWindows().setLocationRelativeTo(null);
                    centerPanel.getMainWindows().getMainPanel().setBounds(0, 0, centerPanel.getMainWindows().getCurrentSize().width, centerPanel.getMainWindows().getCurrentSize().height);
                    centerPanel.getMainWindows().setWindowsState(0);
                    centerPanel.getMaximize().setIcon(centerPanel.getMaximizeB());
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (centerPanel.getMainWindows().getWindowsState() == 0) {
                    centerPanel.getMaximize().setIcon(centerPanel.getMaximizeW());
                    centerPanel.getMaximize().setBackground(Color.gray);
                } else {
                    centerPanel.getMaximize().setIcon(centerPanel.getRestoreW());
                    centerPanel.getMaximize().setBackground(Color.gray);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (centerPanel.getMainWindows().getWindowsState() == 0) {
                    centerPanel.getMaximize().setIcon(centerPanel.getMaximizeB());
                    centerPanel.getMaximize().setBackground(Color.white);
                } else {
                    centerPanel.getMaximize().setIcon(centerPanel.getRestoreB());
                    centerPanel.getMaximize().setBackground(Color.white);
                }
            }
        });

        centerPanel.getClose().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("[BUSCenterPanel]: Close");
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                centerPanel.getClose().setIcon(centerPanel.getCloseW());
                centerPanel.getClose().setBackground(Color.red);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                centerPanel.getClose().setIcon(centerPanel.getCloseB());
                centerPanel.getClose().setBackground(Color.white);
            }
        });
    }

    private void contentEvents() {
        
    }

}
