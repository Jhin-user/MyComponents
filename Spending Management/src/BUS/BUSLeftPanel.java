package BUS;

import GUI.LeftPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 *
 * @author Jhin
 */
public class BUSLeftPanel {

    private LeftPanel leftPanel;

    public BUSLeftPanel(LeftPanel leftPanel) {
        this.leftPanel = leftPanel;
        iconEvents();
    }

    private void iconEvents() {
        leftPanel.getAvatar().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                leftPanel.getMainWindows().getHoverPanel().setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                leftPanel.getMainWindows().getHoverPanel().setVisible(false);
            }
        });

        leftPanel.getAvatar().addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                leftPanel.getMainWindows().getHoverPanel().setBounds(e.getX() + 25, e.getY() + 25, leftPanel.getMainWindows().getHoverPanel().getSize().width, leftPanel.getMainWindows().getHoverPanel().getSize().height);
            }
        });
    }

}
