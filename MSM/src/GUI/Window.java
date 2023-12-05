package GUI;

import BUS.BUSControlWindows;
import BUS.BUSFeature;
import GUISupport.RealTimePanel;
import Images.ImageSupport;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.time.LocalDate;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Jhin
 */
public class Window extends JFrame {

    private final Rectangle screenRect = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
    private final Dimension fullScreenSize = new Dimension(screenRect.width, screenRect.height);
    private final Image image = new ImageIcon(getClass().getResource("../Images/Viego.png")).getImage();
    private final Image windowsIcon = new ImageIcon(getClass().getResource("../Images/search.png")).getImage();
    private final ImageIcon closeIcon = new ImageIcon(getClass().getResource("../Images/close.png"));
    private final ImageIcon extendIcon = new ImageIcon(getClass().getResource("../Images/extend.png"));
    private final ImageIcon miniIcon = new ImageIcon(getClass().getResource("../Images/mini.png"));
    private final ImageIcon hideIcon = new ImageIcon(getClass().getResource("../Images/hide.png"));
    private final ImageIcon settingIcon = new ImageIcon(getClass().getResource("../Images/setting.png"));

    private final String[] cardName = new String[]{"Home", "Add", "Update", "Setting"};
    private final ImageIcon[] featureIcon = new ImageIcon[]{
        new ImageIcon(getClass().getResource("../Images/home.png")),
        new ImageIcon(getClass().getResource("../Images/add.png")),};

    private final Font dateTimeFont = new Font("Monospaced", 1, 24);
    private final ImageIcon dayIcon = new ImageIcon(getClass().getResource("../Images/day.png"));
    private final ImageIcon nightIcon = new ImageIcon(getClass().getResource("../Images/night.png"));

    private Dimension currentSize = new Dimension(1024, 720);
    private Point currentLocation;
    /**
     * 0. Normal 1. Extend
     */
    private int windowsState = 0;

    private JPanel backgroundPanel;

    private JLabel close, extend, hide, setting;
    private JPanel featurePanel;
    private JLabel[] feature;
    private JPanel centerOfCenter;
    private CardLayout cardLayout;
    private Home home;
    private Add add;
    private Update update;

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public Window() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(windowsIcon);
        setSize(currentSize);
        setLocationRelativeTo(null);
        setLayout(null);
        setUndecorated(true);
        setVisible(true);
        initComponents();
        revalidate();
        new BUSControlWindows(this);
        new BUSFeature(this);
    }

    private void initComponents() {
        /* Get Location */
        currentLocation = getLocation();

        /* -------------------- Main Panel -------------------- */
        backgroundPanel = new JPanel(new BorderLayout(0, 0)) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.drawImage(image, 0, 0, getWidth(), getHeight(), null);
            }
        };
        backgroundPanel.setBounds(0, 0, getWidth(), getHeight());
        backgroundPanel.setOpaque(false);
        add(backgroundPanel);

        /* ---------- East ---------- */
        eastPanel();

        /* ---------- Center ---------- */
        JPanel center = new JPanel(new BorderLayout(0, 0));
        center.setOpaque(false);
        backgroundPanel.add(center, "Center");

        /* North - Center */
        northCenterPanel(center);

        /* Center of Center */
        centerCenterPanel(center);
    }

    /* ---------- Components ---------- */
    private void eastPanel() {
        JPanel east = new JPanel(new BorderLayout(0, 0));
        east.setPreferredSize(new Dimension(50, 0));
        east.setBackground(new Color(200, 200, 200, 75));
        backgroundPanel.add(east, "East");

        JPanel controlPanel = new JPanel(new FlowLayout(3, 7, 7));
        controlPanel.setPreferredSize(new Dimension(50, backgroundPanel.getHeight()));
        controlPanel.setOpaque(false);
        east.add(controlPanel, "Center");

        close = new JLabel(ImageSupport.getSizedIcon(closeIcon, 24, 24));
        close.setPreferredSize(new Dimension(36, 36));
        controlPanel.add(close);

        extend = new JLabel(ImageSupport.getSizedIcon(extendIcon, 24, 24));
        extend.setPreferredSize(new Dimension(36, 36));
        controlPanel.add(extend);

        hide = new JLabel(ImageSupport.getSizedIcon(hideIcon, 30, 30));
        hide.setPreferredSize(new Dimension(36, 36));
        controlPanel.add(hide);

        JPanel settingPanel = new JPanel(new FlowLayout(3, 7, 7));
        settingPanel.setPreferredSize(new Dimension(50, 50));
        settingPanel.setOpaque(false);
        east.add(settingPanel, "South");

        setting = new JLabel(ImageSupport.getSizedIcon(settingIcon, 24, 24));
        setting.setPreferredSize(new Dimension(36, 36));
        settingPanel.add(setting);
    }

    private void northCenterPanel(JPanel center) {
        /* ---------- North of Center ---------- */
        JPanel centerNorth = new JPanel(new GridLayout(3, 1, 0, 5));
        centerNorth.setPreferredSize(new Dimension(0, 180));
        centerNorth.setOpaque(false);
        center.add(centerNorth, "North");

        /* ---------- Row 0 - Feature ---------- */
        featurePanel = new JPanel(new FlowLayout(1, 5, 0));
        featurePanel.setOpaque(false);
        centerNorth.add(featurePanel);

        feature = new JLabel[2];
        for (int i = 0; i < feature.length; i++) {
            feature[i] = new JLabel(ImageSupport.getSizedIcon(featureIcon[i], 30, 30));
            feature[i].setPreferredSize(new Dimension(60, 60));
            feature[i].setName(cardName[i]);
            featurePanel.add(feature[i]);
        }

        /* ---------- Row 1 - Date and Time ---------- */
        JPanel dateTimePanel = new JPanel(new GridLayout(1, 2));
        dateTimePanel.setPreferredSize(new Dimension(0, 60));
        dateTimePanel.setOpaque(false);
        centerNorth.add(dateTimePanel);

        /* Date */
        LocalDate localDate = LocalDate.now();

        JLabel dateLabel = new JLabel(localDate.getDayOfWeek() + ", " + localDate.getMonth() + " " + localDate.getDayOfMonth() + ", " + localDate.getYear(), 0);
        dateLabel.setFont(dateTimeFont);
        dateLabel.setForeground(Color.white);
        dateTimePanel.add(dateLabel);

        /* Time */
        RealTimePanel clock = new RealTimePanel(dayIcon, nightIcon);
        clock.setClockFont(dateTimeFont);
        clock.setClockForeground(Color.white);
        clock.setOpaque(false);
        dateTimePanel.add(clock);

        /* ---------- Row 2 - Search, Back ---------- */
        JPanel filterPanel = new JPanel();
        filterPanel.setPreferredSize(new Dimension(0, 60));
        centerNorth.add(filterPanel);

        filterPanel.add(new JLabel("from day to day; sort by id, sort by day, sort by price"));
    }

    private void centerCenterPanel(JPanel center) {
        cardLayout = new CardLayout(0, 0);

        centerOfCenter = new JPanel(cardLayout);
        centerOfCenter.setOpaque(false);
        center.add(centerOfCenter, "Center");

        home = new Home(this);
        home.setName("Home");
        centerOfCenter.add(home, home.getName());
    }

    // Method
    public void newAdd() {
        add = new Add(this);
        add.setName("Add");
        centerOfCenter.add(add, add.getName());
    }

    public void newUpdate() {
        update = new Update();
        update.setName("Update");
        centerOfCenter.add(update, update.getName());
    }

    // Getter    
    public JLabel getClose() {
        return close;
    }

    public JLabel getExtend() {
        return extend;
    }

    public JLabel getHide() {
        return hide;
    }

    public JLabel getSetting() {
        return setting;
    }

    public JPanel getFeaturePanel() {
        return featurePanel;
    }

    public ImageIcon getExtendIcon() {
        return extendIcon;
    }

    public ImageIcon getMiniIcon() {
        return miniIcon;
    }

    public Dimension getFullScreenSize() {
        return fullScreenSize;
    }

    public Dimension getCurrentSize() {
        return currentSize;
    }

    public int getWindowsState() {
        return windowsState;
    }

    public Point getCurrentLocation() {
        return currentLocation;
    }

    public JPanel getBackgroundPanel() {
        return backgroundPanel;
    }

    public JLabel[] getFeature() {
        return feature;
    }

    public ImageIcon[] getFeatureIcon() {
        return featureIcon;
    }

    public JPanel getCenterOfCenter() {
        return centerOfCenter;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public Home getHome() {
        return home;
    }

    // Setter
    public void setWindowsState(int windowsState) {
        this.windowsState = windowsState;
    }

    public void setCurrentLocation(Point currentLocation) {
        this.currentLocation = currentLocation;
    }

}
