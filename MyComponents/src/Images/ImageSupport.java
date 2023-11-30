package Images;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Jhin
 */
public class ImageSupport {

    public static ImageIcon getSizedIcon(ImageIcon originalIcon, int targetWidth, int targetHeight) {
        Image originalImage = originalIcon.getImage();
        Image sizedImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(sizedImage);
    }
}
