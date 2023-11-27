package MyComponents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

/**
 *
 * @author Jhin
 */
public class RadiusPanel extends JPanel {

    private int radius, borderWidth;
    private Color backgroundColor;
    private Color[] borderColor;
    private LinearGradientPaint gradient;

    public RadiusPanel(int radius) {
        setOpaque(false);
        this.radius = radius;
        this.borderWidth = 1;
        this.backgroundColor = new Color(225, 225, 225, 150);
        this.borderColor = new Color[]{new Color(255, 165, 0, 175)};
    }

    public RadiusPanel(int radius, int borderWidth) {
        setOpaque(false);
        this.radius = radius;
        this.borderWidth = borderWidth;
        this.backgroundColor = new Color(225, 225, 225, 150);
        this.borderColor = new Color[]{new Color(255, 165, 0, 175)};
    }

    public RadiusPanel(int radius, int borderWidth, Color backgroundColor) {
        setOpaque(false);
        this.radius = radius;
        this.borderWidth = borderWidth;
        this.backgroundColor = backgroundColor;
        this.borderColor = new Color[]{new Color(255, 165, 0, 175)};
    }

    public RadiusPanel(int radius, int borderWidth, Color[] borderColor) {
        if (borderColor.length < 2) {
            System.out.println("Border have least 2 color");
            setOpaque(false);
            this.radius = radius;
            this.borderWidth = borderWidth;
            this.backgroundColor = new Color(225, 225, 225, 150);
            this.borderColor = new Color[]{new Color(255, 165, 0, 175)};
            return;
        }
        setOpaque(false);
        this.radius = radius;
        this.borderWidth = borderWidth;
        this.backgroundColor = new Color(225, 225, 225, 150);
        this.borderColor = borderColor;
    }

    public RadiusPanel(int radius, int borderWidth, Color backgroundColor, Color[] borderColor) {
        if (borderColor.length < 2) {
            System.out.println("Border have least 2 color");
            setOpaque(false);
            this.radius = radius;
            this.borderWidth = borderWidth;
            this.backgroundColor = backgroundColor;
            this.borderColor = new Color[]{new Color(255, 165, 0, 175)};
            return;
        }
        setOpaque(false);
        this.radius = radius;
        this.borderWidth = borderWidth;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw backgound
        g2.setColor(backgroundColor);
        g2.fill(new RoundRectangle2D.Double(borderWidth / 2, borderWidth / 2, width - borderWidth, height - borderWidth, radius, radius));

        /* ---------- Màu Gradient ---------- */
        if (borderColor.length != 1) {
            float[] fraction = new float[borderColor.length];
            for (int i = 0; i < borderColor.length; i++) {
                fraction[i] = (float) 1 / borderColor.length * (i + 1);
            }

            gradient = new LinearGradientPaint(
                    0, 0, getWidth(), getHeight(),
                    fraction,
                    borderColor
            );
        }

        // Draw border
        if (borderColor.length != 1) {
            g2.setPaint(gradient);
            g2.setStroke(new BasicStroke(borderWidth));
            g2.drawRoundRect(borderWidth / 2, borderWidth / 2, width - borderWidth, height - borderWidth, radius, radius);
        } else {
            g2.setPaint(borderColor[0]);
            g2.setStroke(new BasicStroke(borderWidth));
            g2.drawRoundRect(borderWidth / 2, borderWidth / 2, width - borderWidth, height - borderWidth, radius, radius);
        }

        g2.dispose();
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setBorderColor(Color[] borderColor) {
        this.borderColor = borderColor;
    }

    public void test() {
        System.out.println("S: " + getPreferredSize());
    }
}
