package GUISupport;

import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author Jhin
 */
public class Card extends JPanel {

    private String cardName;

    public Card(String cardName) {
        this.cardName = cardName;
    }

    public String getCardName() {
        return cardName;
    }

}
