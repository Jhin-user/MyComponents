package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Jhin
 */
public class Window extends JFrame {

    private final Image background = new ImageIcon(getClass().getResource("../Images/Viego.png")).getImage();

    private JPanel backgroundPanel;

    public Window() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 720);
        setLocationRelativeTo(null);
        setLayout(null);
//        setUndecorated(true);
        setVisible(true);
        initComponents();
        revalidate();
    }

    private void initComponents() {
        /* -------------------- Main Panel -------------------- */
        backgroundPanel = new JPanel(new BorderLayout(0, 0)) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
            }
        };
        backgroundPanel.setBounds(0, 0, getWidth(), getHeight());
        add(backgroundPanel);

        /* ---------- Test ---------- */
        JPanel radius = new JPanel() {
            int w = getWidth(), h = getHeight(), r = 20;

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                setOpaque(false);

                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.drawRoundRect(0, 0, w, h, r, r);
                g2.setColor(Color.red);
                g2.dispose();
            }
        };
        radius.setPreferredSize(new Dimension(200, 40));
        backgroundPanel.add(radius, "North");
    }

}
