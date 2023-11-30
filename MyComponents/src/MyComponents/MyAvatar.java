package MyComponents;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Jhin
 */
public class MyAvatar extends JPanel {

    private Icon image;
    private int borderSize;
    private int borderSpace;
    private Color borderColor1, borderColor2;

    public MyAvatar(Icon image, int borderSize, int borderSpace, Color borderColor1, Color borderColor2, int diameter) {
        this.image = image;
        this.borderSize = borderSize;
        this.borderSpace = borderSpace;
        this.borderColor1 = borderColor1;
        this.borderColor2 = borderColor2;
        setPreferredSize(new Dimension(diameter, diameter));
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int diameter = Math.min(width, height);
        int x = (width - diameter) / 2;
        int y = (height - diameter) / 2;
        if (isOpaque()) {
            g2.setColor(getBackground());
            g2.fillOval(x, y, diameter, diameter);
        }

        Area area = new Area(new Ellipse2D.Double(x, y, diameter, diameter));
        int s = diameter - (borderSize * 2);
        area.subtract(new Area(new Ellipse2D.Double(x + borderSize, y + borderSize, s, s)));
        g2.setPaint(new GradientPaint(0, 0, borderColor1, width, height, borderColor2));
        g2.fill(area);

        if (image != null) {
            int imageDiameter = Math.min(width, height) - (borderSize * 2 + borderSpace * 2);
            int xImage = (width - imageDiameter) / 2;
            int yImage = (height - imageDiameter) / 2;
            Rectangle size = getAutoSize(image, imageDiameter);
            BufferedImage img = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2_img = img.createGraphics();
            g2_img.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2_img.fillOval(0, 0, imageDiameter, imageDiameter);

            Composite composite = g2_img.getComposite();
            g2_img.setComposite(AlphaComposite.SrcIn);
            g2_img.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2_img.drawImage(((ImageIcon) image).getImage(), size.x, size.y, size.width, size.height, null);
            g2_img.setComposite(composite);
            g2_img.dispose();
            g2.drawImage(img, xImage, yImage, null);
        }

    }

    private Rectangle getAutoSize(Icon image, int size) {
        int w = size;
        int h = size;
        int iw = image.getIconWidth();
        int ih = image.getIconHeight();
        double xScale = (double) w / iw;
        double yScale = (double) h / ih;
        double scale = Math.max(xScale, yScale);
        int width = (int) (scale * iw);
        int height = (int) (scale * ih);
        
        if (width < 1) {
            width = 1;
        }
        if (height < 1) {
            height = 1;
        }
        int cw = size;
        int ch = size;
        int x = (cw - width) / 2;
        int y = (ch - height) / 2;
        return new Rectangle(new Point(x, y), new Dimension(width, height));
    }

    public void setBorderSize(int borderSize) {
        this.borderSize = borderSize;
        repaint();
    }

}
