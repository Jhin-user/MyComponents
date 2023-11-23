package GUI;

import BUS.BUSLeftPanel;
import GUISupport.Avatar;
import GUISupport.RipplePanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Jhin
 */
public class LeftPanel extends JPanel {

    private final Icon image = new ImageIcon(getClass().getResource("../Assets/Icon/Windows Icon.png"));
    private final Color c1 = Color.decode("#2bff00"), c2 = Color.decode("#ff4800"), buttonColor = new Color(0, 149, 255, 128);
    private final Dimension btnSize = new Dimension(150, 50);
    private final String[] btnStr = new String[]{"Buy", "Add"};
    private final ImageIcon[] btnIcon = new ImageIcon[]{
        new ImageIcon(getClass().getResource("../Assets/Icon/btnIcon.png")),
        new ImageIcon(getClass().getResource("../Assets/Icon/btnIcon.png"))
    };

    private MainWindows mainWindows;

    private Avatar avatar;
    private RipplePanel[] rippleBtn;
    private JLabel[] lbBtn;

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public LeftPanel(MainWindows mainWindows) {
        this.mainWindows = mainWindows;
        initComponents();
        new BUSLeftPanel(this);
    }

    private void initComponents() {
        setPreferredSize(new Dimension(150, 960));
        setLayout(new BorderLayout(0, 0));
        setBackground(new Color(255, 220, 0, 160));

        // Avatar Panel
        JPanel avatarPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 25, 25));
        avatarPanel.setPreferredSize(new Dimension(150, 150));
        avatarPanel.setOpaque(false);
        add(avatarPanel, BorderLayout.NORTH);

        avatar = new Avatar(image, 4, 2, c1, c2, 100);
        avatarPanel.add(avatar);

        // Center Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(3, 0, 1));
        buttonPanel.setOpaque(false);
        add(buttonPanel, BorderLayout.CENTER);

        /* --------------------------------------------------------------------------- */
        /*
         * When add click event for Ripple Panel
         * Check the Riple panel if it not animated
         */
        /* --------------------------------------------------------------------------- */
        rippleBtn = new RipplePanel[btnStr.length];
        lbBtn = new JLabel[btnStr.length];
        for (int i = 0; i < btnStr.length; i++) {
            rippleBtn[i] = new RipplePanel(new FlowLayout(3, 20, 0), Color.decode("#7fd4ff"), buttonColor);
            rippleBtn[i].setPreferredSize(btnSize);
            rippleBtn[i].setBackground(buttonColor);
            lbBtn[i] = new JLabel(" " + btnStr[i], btnIcon[i], JLabel.LEFT);
            lbBtn[i].setPreferredSize(btnSize);
            lbBtn[i].setFont(new Font("Consolas", Font.BOLD, 18));
            lbBtn[i].setForeground(Color.decode("#FFFFFF"));
            lbBtn[i].setOpaque(false);
            rippleBtn[i].add(lbBtn[i]);
            buttonPanel.add(rippleBtn[i]);
        }
    }

    public MainWindows getMainWindows() {
        return mainWindows;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public Color getButtonColor() {
        return buttonColor;
    }

}
