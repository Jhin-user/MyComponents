package GUISupport;

import javax.swing.Icon;
import javax.swing.JLabel;

/**
 *
 * @author Jhin
 */
public class FeatureItem extends JLabel {

    private int index;

    public FeatureItem(Icon icon, int index) {
        super(icon);
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

}
