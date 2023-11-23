package GUISupport;

import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author Jhin
 */
public class HoverPanel extends JPanel {

    public HoverPanel() {
        initComponents();
    }

    private void initComponents() {
        setSize(200, 300);
        setBackground(new Color(245, 122, 3, 200));
        setLocation(0, 0);
        setVisible(false);
    }
}
