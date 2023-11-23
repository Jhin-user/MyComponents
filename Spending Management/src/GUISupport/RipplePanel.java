package GUISupport;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Jhin
 */
public class RipplePanel extends JPanel {

    /* ------------------------------------------------------------------------------------ */
 /*
     *  fillOval : Tạo shape đặc
     *  drawOval : Tạo shape rỗng
     */
 /* ------------------------------------------------------------------------------------ */
    private Point center;
    private int radius;
    private Color color, background;

    // Just use for click
    public RipplePanel(Color background) {
        this.background = background;
        center = new Point();
        radius = 0;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                center = e.getPoint();
                animation();
            }
        });
    }

    // Use for add component
    public RipplePanel(FlowLayout layout, Color color, Color background) {
        setLayout(layout);
        this.color = color;
        this.background = background;
        center = new Point();
        radius = 0;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                center = e.getPoint();
                animation();
            }
        });
    }

    @SuppressWarnings("SleepWhileInLoop")
    private void animation() {
        new Thread(() -> {
            for (int i = 1; i <= 35; i++) {
                radius = i * 5;
                repaint();
                try {
                    Thread.sleep(8);
                } catch (InterruptedException ex) {
                }
            }
            radius = 0;
            center.setLocation(-1, -1);
            repaint();
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);

        if (center.x != -1 && center.y != -1) {
            int x = center.x - radius;
            int y = center.y - radius;
            int diameter = radius * 2;
            g2d.fillOval(x, y, diameter, diameter);
        }
    }

}
