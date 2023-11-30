package GUI;

import GUISupport.Card;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;

/**
 *
 * @author Jhin
 */
public class Add extends Card {

    public Add(String cardName) {
        super(cardName);
        initComponent();
    }

    private void initComponent() {
        setLayout(new BorderLayout(0, 0));
        setOpaque(false);
        
        JPanel north = new JPanel(new FlowLayout(0, 0, 0));
        north.setPreferredSize(new Dimension(0, 50));
        north.setOpaque(false);
        add(north, "North");
        
        
    }

}
