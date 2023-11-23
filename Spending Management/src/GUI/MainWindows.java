package GUI;

import GUISupport.HoverPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author Jhin
 */
public class MainWindows extends JFrame {

    private final Rectangle fullScreen = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
    private final Dimension fullScreenSize = new Dimension(fullScreen.width, fullScreen.height);
    private final ImageIcon icon = new ImageIcon(getClass().getResource("../Assets/Icon/Windows Icon.png"));

    private Image backGround = new ImageIcon(getClass().getResource("../Assets/Image/background 1.jpg")).getImage();

    private Dimension currentSize = new Dimension(960, 640);

    private HoverPanel hoverPanel;

    private JPanel mainPanel;
    private LeftPanel leftPanel;
    private JPanel centerPanel;

    /**
     * 0. Normal 1. Full screen
     */
    private int windowsState = 0;

    private int windowsType;

    public MainWindows() {
        windowsType = 2;
        /*
         * 0. Normal
         * 1. Center have background
         * 2. All Frame have background
         */
        initComponents();
    }

    private void initComponents() {
        // Control
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(currentSize);
        setLocationRelativeTo(null);
        setIconImage(icon.getImage());
        setUndecorated(true);
        setLayout(null);

        // Components
        hoverPanel = new HoverPanel();
        add(hoverPanel);

        mainPanel = new JPanel(new BorderLayout(0, 0)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (windowsType == 2) {
                    g.drawImage(backGround, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        mainPanel.setBounds(0, 0, getSize().width, getSize().height);
        mainPanel.setBorder(new LineBorder(Color.black, 1));
        add(mainPanel);

        leftPanel = new LeftPanel(this);
        if (windowsType == 2) {
            leftPanel.setOpaque(false);
        }
        mainPanel.add(leftPanel, BorderLayout.WEST);

        switch (windowsType) {
            case 0 -> {
                centerPanel = new CenterPanel(this);
            }
            case 1 -> {
                centerPanel = new CenterPanelBackground(this);
            }
            case 2 -> {
                centerPanel = new CenterPanelBackground(this);
                centerPanel.setOpaque(false);
            }
            default ->
                throw new AssertionError();
        }

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // Getter
    public Dimension getFullScreenSize() {
        return fullScreenSize;
    }

    public Dimension getCurrentSize() {
        return currentSize;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public LeftPanel getLeftPanel() {
        return leftPanel;
    }

    public HoverPanel getHoverPanel() {
        return hoverPanel;
    }

    public int getWindowsState() {
        return windowsState;
    }

    public int getWindowsType() {
        return windowsType;
    }

    // Setter
    public void setWindowsState(int windowsState) {
        this.windowsState = windowsState;
    }

    public void setType(int windowsType) {
        this.windowsType = windowsType;
    }

}
