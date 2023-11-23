package GUISupport;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

/**
 *
 * @author Jhin
 */
public class RadiusPanel extends JPanel {

    private int radius;
    private int radiusWidth, radiusHeight;
    private int borderWidth;
    private Color c1, c2;

    public RadiusPanel(int radius, int borderWitdh) {
        this.radius = radius;
        this.borderWidth = borderWitdh;
        this.c1 = Color.decode("#FFA500");
        this.c2 = Color.decode("#FFC0CB");
        initComponents();
    }

    public RadiusPanel(int radiusWidth, int radiusHeight, int borderWidth) {
        this.radius = 0;
        this.radiusWidth = radiusWidth;
        this.radiusHeight = radiusHeight;
        this.borderWidth = borderWidth;
        this.c1 = Color.decode("#FFA500");
        this.c2 = Color.decode("#FFC0CB");
        initComponents();
    }

    public RadiusPanel(int radius, int borderWitdh, Color c1, Color c2) {
        this.radius = radius;
        this.borderWidth = borderWitdh;
        this.c1 = c1;
        this.c2 = c2;
        initComponents();
    }

    private void initComponents() {
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int arcWidth;
        int arcHeight;

        if (radius > 0) {
            arcWidth = radius;
            arcHeight = radius;
        } else {
            arcWidth = radiusWidth;
            arcHeight = radiusHeight;
        }

        Graphics2D g2 = (Graphics2D) g.create();
        RoundRectangle2D roundedRect = new RoundRectangle2D.Float(borderWidth / 2, borderWidth / 2, getWidth() - 1 - borderWidth, getHeight() - 1 - borderWidth, arcWidth, arcHeight);

        // Thiết lập độ dày của viền
        g2.setStroke(new BasicStroke(borderWidth));

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(roundedRect);
        g2.setPaint(new GradientPaint(0, 0, c1, getWidth(), getHeight(), c2));
        g2.draw(roundedRect);
        g2.dispose();
    }
}
