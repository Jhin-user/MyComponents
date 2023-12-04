package RunAsWindowsUI;

import MyComponents.BUSMyTable;
import MyComponents.MyAvatar;
import MyComponents.MyTable;
import MyComponents.MyTransparentcyCombobox;
import MyComponents.RadiusPanel;
import MyComponents.RealTimePanel;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.AdjustmentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

/**
 *
 * @author Jhin
 */
public class RunTheGUI extends JFrame {

    private final Image background = new ImageIcon(getClass().getResource("../Images/back 2.jpg")).getImage();

    private JPanel backgroundPanel;

    private JDateChooser dateChooser;

    public RunTheGUI() {
        setDefaultCloseOperation(3);
        setSize(1024, 720);
        setPreferredSize(new Dimension(1024, 750));
        setLocationRelativeTo(null);
        setTitle("My Components");
        setLayout(new BorderLayout(0, 0));
        setUndecorated(true);
        setVisible(true);

        backgroundPanel = new JPanel(new FlowLayout(3, 0, 0)) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
            }
        };
        backgroundPanel.setPreferredSize(new Dimension(getWidth() - 16, getHeight() - 40));
        backgroundPanel.setOpaque(false);
        backgroundPanel.addMouseListener(new MouseAdapter() {
            private static final long DOUBLE_CLICK_TIME_THRESHOLD = 300; // Thời gian giới hạn để xem là double-click (tính theo mili giây)
            private long lastClickTime = 0;

            @Override
            public void mouseClicked(MouseEvent e) {
                long currentTime = System.currentTimeMillis();

                if (currentTime - lastClickTime < DOUBLE_CLICK_TIME_THRESHOLD) {
                    // Double-click đã xảy ra
                    handleDoubleClick();
                }

                lastClickTime = currentTime;
            }

            private void handleDoubleClick() {
                System.out.println("Double Clicked! Exited");
                System.exit(0);
            }
        });
        add(backgroundPanel, "Center");

        /* -------------------- Components -------------------- */
        int component = 7;
        switch (component) {
            case -1 ->
                createDateChooser();
            case 0 ->
                creatMyTable();
            case 1 ->
                createAvatar();
            case 2 ->
                createHoverPanel();
            case 3 ->
                creatRightMenuAnimation();
            case 4 ->
                creatMenuHover();
            case 5 ->
                createRadiusPanel();
            case 6 ->
                createRealTimeClock();
            case 7 ->
                createTransparentcyCombobox();
            default -> {
                System.out.println("No Components Choosen");
            }
        }
        /* ---------------------------------------------------- */

        revalidate();
    }

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            EventQueue.invokeLater(() -> {
                new RunTheGUI();
            });
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
        }
    }

    private void createDateChooser() {

        dateChooser = new JDateChooser();
        dateChooser.setPreferredSize(new Dimension(300, 40));
        dateChooser.setFont(new Font("Consolas", 0, 16));
        dateChooser.getCalendarButton().setIcon(new ImageIcon(getClass().getResource("../Images/find.png")));
        backgroundPanel.add(dateChooser);

        /*
         * Calendar.DAY_OF_WEEK
         * 0 - Monday ... 
         * 
         * Calendar.DAY_OF_MONTH
         * 1 - 1 ...
         * 
         * Calendar.DAY_OF_WEEK_OF_MONTH
         * day / 7 + 1
         * 
         * Calendar.WEEK_OF_MONTH by line
         *                 1  2 -> line 1 -> 1
         *  3  4  5  6  7  8  9 -> line 2 -> 2
         * 10 11 12 13 14 15 16 -> line 3 -> 3
         * 17 18 19 20 21 22 23 -> line 4 -> 4
         * 24 25 26 27 28 29 30 -> line 5 -> 5
         * 31                   -> line 6 -> 6
         * 
         * Calendar.DAY_OF_YEAR
         * 1 ... 365
         * 
         * Calendar.MONTH
         * January - 0
         * ...
         * December - 11
         * 
         * Calendar.YEAR
         * year
         */
    }

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    private void creatMyTable() {
        JScrollPane jcp = new JScrollPane();
        jcp.setPreferredSize(new Dimension(getWidth() - 16, getHeight() - 40));
        jcp.getVerticalScrollBar().addAdjustmentListener((AdjustmentEvent e) -> {
            // Điều chỉnh tốc độ cuộn bằng cách nhân giá trị mới với hệ số
            e.getAdjustable().setUnitIncrement(10);
        });
        /* ---------- Visible ScrollPane ---------- */
        boolean scrollPaneVisible = false;
        if (!scrollPaneVisible) {
            jcp.setOpaque(false);
            jcp.getViewport().setOpaque(false);
//            jcp.getViewport().setBackground(new Color(0, 0, 0, 0));
            jcp.getVerticalScrollBar().setOpaque(false);
        }
        /* ---------------------------------------- */
        backgroundPanel.add(jcp);

        /* ---------- Make header transparentcy ---------- */
        UIManager.put("TableHeader.background", new Color(0, 0, 0, 0));
        /* ----------------------------------------------- */
        MyTable myTable = new MyTable();
        myTable.setHeaders(new String[]{"Col 1", "Col 2", "Col 3"});
        /* ---------- Transparentcy Table ---------- */
        boolean tranparency = true;
        if (tranparency) {
            myTable.setOpaque(false);
            myTable.setHeadersProperties(new Color(255, 165, 0, 150), Color.white, new Font("Monospaced", 1, 20), 30);
            myTable.setTableProperties(new Color(192, 192, 192, 192), Color.white, new Font("Consolas", 0, 18), 50);
        } else {
            myTable.setHeadersProperties(Color.orange, myTable.getTableHeader().getForeground(), new Font("Monospaced", 1, 20), 30);
            myTable.setTableProperties(null, Color.pink, new Font("Consolas", 0, 18), 50);

        }

        /* ---------- Data test ---------- */
        String[][] dataTest = new String[50][3];
        for (int i = 0; i < 50; i++) {
            String[] s = new String[3];
            for (int j = 0; j < 3; j++) {
                s[j] = "Row " + i + ", Col " + j;
            }
            dataTest[i] = s;
        }
        /* ------------------------------- */
        myTable.setData(dataTest);
        jcp.setViewportView(myTable);

        new BUSMyTable(myTable);
    }

    private void createAvatar() {
        Icon image = new ImageIcon(getClass().getResource("../Images/back 3.jpg"));
        MyAvatar avatar = new MyAvatar(image, 2, 5, Color.yellow, Color.green, 500);

        backgroundPanel.add(avatar);
    }

    /* ---------------------------------------- */
    JPanel visiblePanel;
    private int holderX = 300, holderY = 200;

    private void createHoverPanel() {
        setLayout(null);

        backgroundPanel.setBounds(0, 0, 1024, 720);
        backgroundPanel.setLayout(null);

        visiblePanel = new JPanel();
        visiblePanel.setBounds(0, 0, 150, 150);
        visiblePanel.setBackground(new Color(255, 165, 0, 128));
        visiblePanel.setVisible(false);
        backgroundPanel.add(visiblePanel);

        JPanel panel = new JPanel();
        panel.setBounds(holderX, holderY, 412, 320);
        panel.setBorder(new LineBorder(Color.cyan, 2));
        panel.setOpaque(false);
        backgroundPanel.add(panel);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                visiblePanel.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                visiblePanel.setVisible(false);
            }
        });

        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                System.out.println(String.format("Point: %d, %d", holderX + e.getX(), holderY + e.getY()));
                visiblePanel.setBounds(holderX + e.getX(), holderY + e.getY(), 150, 150);
            }
        });
    }

    /* ------------------------------------------------------------ */
    private JPanel rightMenu;

    private void creatRightMenuAnimation() {
        backgroundPanel.setLayout(new BorderLayout(0, 0));

        rightMenu = new JPanel(new FlowLayout(3, 0, 3));
        rightMenu.setPreferredSize(new Dimension(0, 0));
        backgroundPanel.add(rightMenu, "East");

        JLabel[] lb = new JLabel[5];
        for (int i = 0; i < 5; i++) {
            lb[i] = new JLabel("   HEHE " + i);
            lb[i].setPreferredSize(new Dimension(300, 40));
            lb[i].setOpaque(true);
            lb[i].setBackground(Color.gray);
            lb[i].setForeground(Color.white);
            lb[i].setFont(new Font("Consolas", 1, 16));
            rightMenu.add(lb[i]);
        }

        JPanel south = new JPanel(new FlowLayout(FlowLayout.TRAILING, 0, 0));
        backgroundPanel.add(south, "South");

        JButton btn = new JButton("Show menu");
        btn.setPreferredSize(new Dimension(100, 40));
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Thread() {
                    @Override
                    @SuppressWarnings("SleepWhileInLoop")
                    public void run() {
                        if (rightMenu.getPreferredSize().width == 0) {
                            for (int i = 5; i <= 300; i += 5) {
                                rightMenu.setPreferredSize(new Dimension(i, 0));
                                backgroundPanel.revalidate();
                                try {
                                    Thread.sleep(6);
                                } catch (InterruptedException ex) {
                                }
                            }
                            return;
                        }
                        if (rightMenu.getPreferredSize().width > 0) {
                            for (int i = 295; i >= 0; i -= 5) {
                                rightMenu.setPreferredSize(new Dimension(i, 0));
                                backgroundPanel.revalidate();
                                try {
                                    Thread.sleep(6);
                                } catch (InterruptedException ex) {
                                }
                            }
                        }
                    }
                }.start();
            }
        });
        south.add(btn);

        JPanel center = new JPanel(new FlowLayout(3, 4, 3));
        backgroundPanel.add(center, "Center");

        JLabel[] centerLb = new JLabel[12];
        for (int i = 0; i < 12; i++) {
            centerLb[i] = new JLabel("  Label " + i);
            centerLb[i].setPreferredSize(new Dimension(150, 40));
            centerLb[i].setFont(new Font("Consolas", 1, 16));
            centerLb[i].setOpaque(true);
            centerLb[i].setBackground(Color.white);
            center.add(centerLb[i]);
        }
    }

    /* ------------------------------------------------------------ */
    private JPanel leftMenu;

    private void creatMenuHover() {
        setLayout(null);

        leftMenu = new JPanel(new FlowLayout(3, 0, 3));
        leftMenu.setBounds(0, 0, 40, 720);
        leftMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                new Thread() {
                    @Override
                    @SuppressWarnings("SleepWhileInLoop")
                    public void run() {
                        for (int i = 45; i <= 250; i += 5) {
                            leftMenu.setBounds(0, 0, i, 720);
                            try {
                                Thread.sleep(6);
                            } catch (InterruptedException e) {
                            }
                        }
                    }
                }.start();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                new Thread() {
                    @Override
                    @SuppressWarnings("SleepWhileInLoop")
                    public void run() {
                        for (int i = 245; i >= 40; i -= 5) {
                            leftMenu.setBounds(0, 0, i, 720);
                            try {
                                Thread.sleep(6);
                            } catch (InterruptedException e) {
                            }
                        }
                    }
                }.start();
            }
        });
        add(leftMenu);

        ImageIcon img = new ImageIcon(getClass().getResource("../Images/find.png"));
        JPanel[] menuItem = new JPanel[4];
        JLabel[] menuIcon = new JLabel[4];
        JLabel[] menuText = new JLabel[4];
        for (int i = 0; i < 4; i++) {
            menuItem[i] = new JPanel(new FlowLayout(3, 0, 0));
            menuItem[i].setPreferredSize(new Dimension(250, 40));
            menuItem[i].setBackground(Color.orange);
            leftMenu.add(menuItem[i]);

            menuIcon[i] = new JLabel(img);
            menuIcon[i].setPreferredSize(new Dimension(40, 40));
            menuItem[i].add(menuIcon[i]);

            menuText[i] = new JLabel("label " + i, JLabel.CENTER);
            menuText[i].setPreferredSize(new Dimension(210, 40));
            menuItem[i].add(menuText[i]);
        }

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, 1024, 720);
        mainPanel.setBackground(Color.pink);
        add(mainPanel);
    }

    private void createRadiusPanel() {
        backgroundPanel.setLayout(new FlowLayout(3, 10, 10));

        RadiusPanel radiusPanel = new RadiusPanel(45, 4, new Color[]{});
        radiusPanel.setPreferredSize(new Dimension(300, 40));
        radiusPanel.setBackgroundColor(Color.lightGray);
        radiusPanel.setBorderColor(new Color[]{Color.pink, Color.orange});
        backgroundPanel.add(radiusPanel);
    }

    private void createRealTimeClock() {
        RealTimePanel realTimePanel = new RealTimePanel();
        realTimePanel.setPreferredSize(new Dimension(50, 50));
        realTimePanel.setOpaque(false);
        backgroundPanel.add(realTimePanel);
    }

    private void createTransparentcyCombobox() {
        MyTransparentcyCombobox transparentcyCombobox = new MyTransparentcyCombobox(new Integer[]{1, 2, 3});
        transparentcyCombobox.setPreferredSize(new Dimension(200, 40));
        transparentcyCombobox.setFont(new Font("Consolas", 1, 20));
        backgroundPanel.add(transparentcyCombobox);
    }
}
