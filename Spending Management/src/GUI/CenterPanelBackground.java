package GUI;

import BUS.BUSCenterPanelBackground;
import GUISupport.MyTransparencyTable;
import GUISupport.RadiusPanel;
import GUISupport.RealTimeClock;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.AdjustmentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Jhin
 */
public class CenterPanelBackground extends JPanel {

    private final Icon closeB = new ImageIcon(getClass().getResource("../Assets/Icon/closeBlack.png"));
    private final Icon closeW = new ImageIcon(getClass().getResource("../Assets/Icon/closeWhite.png"));
    private final Icon maximizeB = new ImageIcon(getClass().getResource("../Assets/Icon/maxiBlack.png"));
    private final Icon maximizeW = new ImageIcon(getClass().getResource("../Assets/Icon/maxiWhite.png"));
    private final Icon restoreB = new ImageIcon(getClass().getResource("../Assets/Icon/restoreBlack.png"));
    private final Icon restoreW = new ImageIcon(getClass().getResource("../Assets/Icon/restoreWhite.png"));
    private final Icon miniB = new ImageIcon(getClass().getResource("../Assets/Icon/miniBlack.png"));
    private final Icon miniW = new ImageIcon(getClass().getResource("../Assets/Icon/miniWhite.png"));
    private final Icon find = new ImageIcon(getClass().getResource("../Assets/Icon/find.png"));
    private final Icon findHover = new ImageIcon(getClass().getResource("../Assets/Icon/find hover.gif"));
    private final Icon finding = new ImageIcon(getClass().getResource("../Assets/Icon/finding.gif"));
    private final Icon notfound = new ImageIcon(getClass().getResource("../Assets/Icon/not found.gif"));

    private MainWindows mainWindows;

    private Image backGround = new ImageIcon(getClass().getResource("../Assets/Image/background 1.jpg")).getImage();

    private JPanel titlePanel, control;

    private JLabel minimize, maximize, close;

    /**
     *
     */
    private Color headBack = new Color(200, 100, 0, 128);

    public CenterPanelBackground(MainWindows mainWindows) {
        this.mainWindows = mainWindows;
        initComponents();
    }

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    private void initComponents() {
        setPreferredSize(new Dimension(0, 0));
        setLayout(new BorderLayout(0, 0));

        initTitlePanel();
        initContentPanel();
        new BUSCenterPanelBackground(this);
    }

    private void initTitlePanel() {
        titlePanel = new JPanel(new BorderLayout(0, 0));
        titlePanel.setPreferredSize(new Dimension(0, 40));
        titlePanel.setOpaque(false);
        add(titlePanel, BorderLayout.NORTH);

        titlePanel.add(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                setPreferredSize(new Dimension(20, 0));
                g.setColor(Color.lightGray);
            }
        }, "West");

        /*
         * Font
         * - Monospaced
         * - Calibri
         */
        JLabel title = new JLabel("Spending Month Use");
        title.setFont(new Font("Monospaced", Font.BOLD, 24));
        title.setForeground(Color.yellow);
        titlePanel.add(title, "Center");

        control = new JPanel(new GridLayout(1, 3, 0, 0));
        control.setPreferredSize(new Dimension(120, 60));
        control.setOpaque(false);
        titlePanel.add(control, BorderLayout.EAST);

        minimize = new JLabel(miniW);
        minimize.setPreferredSize(new Dimension(40, 30));
        minimize.setOpaque(false);
        control.add(minimize);

        maximize = new JLabel(maximizeW);
        maximize.setPreferredSize(new Dimension(40, 30));
        maximize.setOpaque(false);
        control.add(maximize);

        close = new JLabel(closeW);
        close.setPreferredSize(new Dimension(40, 30));
        close.setOpaque(false);
        control.add(close);
    }

    private void initContentPanel() {
        JPanel contentPanel = new JPanel(new BorderLayout(0, 0));
        contentPanel.setOpaque(false);
        add(contentPanel, BorderLayout.CENTER);

        /* Content North */
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
        northPanel.setPreferredSize(new Dimension(0, 80));
        northPanel.setOpaque(false);
        contentPanel.add(northPanel, "North");

        // Date and time
        JPanel datePanel = createDatePanel(new Dimension(500, 80));
        datePanel.setOpaque(false);
        northPanel.add(datePanel);

        RealTimeClock clock = new RealTimeClock(new Dimension(300, 80));
        clock.setOpaque(false);
        northPanel.add(clock);

        /* Content Center */
        JPanel contentCenter = new JPanel(new BorderLayout(20, 20));
        contentCenter.setOpaque(false);
        contentPanel.add(contentCenter, BorderLayout.CENTER);

        contentCenter.add(new JLabel(), BorderLayout.WEST);
        contentCenter.add(new JLabel(), BorderLayout.EAST);
        contentCenter.add(new JLabel(), BorderLayout.SOUTH);

        // Find
        JPanel findPanel = new JPanel(new BorderLayout(5, 0));
        findPanel.setOpaque(false);
        contentCenter.add(findPanel, BorderLayout.NORTH);

        findPanel.add(new JLabel("   "), "West");

        RadiusPanel radiusFind = new RadiusPanel(60, 3, new Color(255, 165, 0, 128), new Color(255, 192, 203, 128));
        radiusFind.setBackground(new Color(128, 128, 128, 128));
        radiusFind.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        radiusFind.setOpaque(false);
        findPanel.add(radiusFind, "Center");

        JTextField findField = new JTextField("Tìm kiếm...");
        findField.setPreferredSize(new Dimension(700, 40));
        findField.setBorder(null);
        findField.setFont(new Font("Consolas", Font.PLAIN, 18));
        findField.setForeground(Color.white);
        findField.setOpaque(false);
        radiusFind.add(findField);

        JLabel findLabel = new JLabel(find);
        findLabel.setPreferredSize(new Dimension(50, 48));
        findPanel.add(findLabel, "East");

        // Table
        UIManager.put("TableHeader.background", new Color(0, 0, 0, 0));
        JPanel tablePanel = new JPanel(new BorderLayout(0, 0));
        tablePanel.setOpaque(false);
        contentCenter.add(tablePanel, BorderLayout.CENTER);

        JScrollPane scr = new JScrollPane();
        scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scr.getVerticalScrollBar().addAdjustmentListener((AdjustmentEvent e) -> {
            // Điều chỉnh tốc độ cuộn bằng cách nhân giá trị mới với hệ số
            e.getAdjustable().setUnitIncrement(10); // Điều chỉnh hệ số tùy thuộc vào tốc độ mong muốn
        });
        scr.getViewport().setOpaque(false);
        scr.getViewport().setBackground(new Color(0, 0, 0, 0));
        scr.setOpaque(false);
        tablePanel.add(scr, BorderLayout.CENTER);

        /* -------------- Romve -------------- */
        String[][] rowData = new String[][]{
            new String[]{"r0 c0", "r0 c1", "r0 c2"},
            new String[]{"r1 c0", "r1 c1", "r1 c2"},
            new String[]{"r2 c0", "r2 c1", "r2 c2"},
            new String[]{"r3 c0", "r0 c1", "r0 c2"},
            new String[]{"r4 c0", "r1 c1", "r1 c2"},
            new String[]{"r5 c0", "r2 c1", "r2 c2"},
            new String[]{"r6 c0", "r0 c1", "r0 c2"},
            new String[]{"r7 c0", "r1 c1", "r1 c2"},
            new String[]{"r8 c0", "r2 c1", "r2 c2"},
            new String[]{"r9 c0", "r0 c1", "r0 c2"},
            new String[]{"r10 c0", "r1 c1", "r1 c2"},
            new String[]{"r11 c0", "r2 c1", "r2 c2"},
            new String[]{"r12 c0", "r0 c1", "r0 c2"},
            new String[]{"r13 c0", "r1 c1", "r1 c2"},
            new String[]{"r14 c0", "r2 c1", "r2 c2"},
            new String[]{"r15 c0", "r0 c1", "r0 c2"},
            new String[]{"r16 c0", "r1 c1", "r1 c2"},
            new String[]{"r17 c0", "r2 c1", "r2 c2"},
            new String[]{"r18 c0", "r0 c1", "r0 c2"},
            new String[]{"r19 c0", "r1 c1", "r1 c2"},
            new String[]{"r20 c0", "r2 c1", "r2 c2"},
            new String[]{"r21 c0", "r0 c1", "r0 c2"},
            new String[]{"r22 c0", "r1 c1", "r1 c2"},
            new String[]{"r23 c0", "r2 c1", "r2 c2"},
            new String[]{"r24 c0", "r0 c1", "r0 c2"},
            new String[]{"r25 c0", "r1 c1", "r1 c2"},
            new String[]{"r26 c0", "r2 c1", "r2 c2"},
            new String[]{"r27 c0", "r0 c1", "r0 c2"},
            new String[]{"r28 c0", "r1 c1", "r1 c2"},
            new String[]{"r29 c0", "r2 c1", "r2 c2"},
            new String[]{"r30 c0", "r0 c1", "r0 c2"},
            new String[]{"r31 c0", "r1 c1", "r1 c2"},
            new String[]{"r32 c0", "r2 c1", "r2 c2"},};
        String[] columnNames = new String[]{"Column 1", "Column 2", "Column 3"};
        /* ----------------------------------- */

//        MyTablePanel table = new MyTablePanel(toArrayList(rowData), columnNames);
//        table.setRowHeight(30);
//        table.setMenuItem(new JMenuItem[]{
//            new JMenuItem("Action 1"),
//            new JMenuItem("Action 2"),
//            new JMenuItem("Action 3"),});
//        scr.setViewportView(table);
        DefaultTableCellRenderer header = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                // Gọi phương thức gốc để nhận giá trị mặc định
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Đặt màu nền cho header
                c.setBackground(headBack);
                // Đặt màu chữ cho header
                c.setForeground(Color.WHITE);
                // Đặt font cho header
                c.setFont(new Font("Consolas", 1, 16));
                // Đặt chiều cao header
                c.setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 30));

                return c;
            }
        };

        DefaultTableCellRenderer row = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                c.setBackground(new Color(0, 0, 0, 75));
//                // Đặt màu nền cho dòng có chỉ số là 1
//                if (row == 1) {
//                    c.setBackground(Color.YELLOW);
//                } else {
//                    // Đặt màu nền mặc định cho các dòng khác
//                    c.setBackground(table.getBackground());
//                }

                return c;
            }
        };

        MyTransparencyTable table = new MyTransparencyTable(rowData, columnNames, header, row);
        table.setOpaque(false);
        table.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Để header không bị vẽ background sai
                revalidate();
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // Để header không bị vẽ background sai
                revalidate();
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Để header không bị vẽ background sai
                revalidate();
                repaint();
            }
        });
        table.getTableHeader().addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                // Để header không bị vẽ background sai
                revalidate();
                repaint();
            }
        });
        table.setHeaderBackground(headBack);
        scr.setViewportView(table);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (mainWindows.getWindowsType() == 1) {
            g.drawImage(backGround, 0, 0, getWidth(), getHeight(), this);
        }
    }

    // Method
    private JPanel createDatePanel(Dimension size) {
        JPanel date = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        date.setPreferredSize(size);

        LocalDate dateNow = LocalDate.now();

        JLabel dateLb;
        dateLb = switch (dateNow.getDayOfMonth()) {
            case 1 ->
                new JLabel(dateNow.getDayOfWeek() + ", " + dateNow.getMonth() + " " + dateNow.getDayOfMonth() + "st, " + dateNow.getYear());
            case 2 ->
                new JLabel(dateNow.getDayOfWeek() + ", " + dateNow.getMonth() + " " + dateNow.getDayOfMonth() + "nt, " + dateNow.getYear());
            case 3 ->
                new JLabel(dateNow.getDayOfWeek() + ", " + dateNow.getMonth() + " " + dateNow.getDayOfMonth() + "rt, " + dateNow.getYear());
            default ->
                new JLabel(dateNow.getDayOfWeek() + ", " + dateNow.getMonth() + " " + dateNow.getDayOfMonth() + "th, " + dateNow.getYear());
        };
        dateLb.setPreferredSize(new Dimension(size.width - 50, size.height));
        dateLb.setFont(new Font("Consolas", Font.BOLD, size.width / 20));
        date.add(dateLb);

        return date;
    }

    private ArrayList<ArrayList<Object>> toArrayList(Object[][] data) {
        ArrayList<ArrayList<Object>> list = new ArrayList<>();
        for (Object[] d : data) {
            ArrayList<Object> l = new ArrayList<>();
            l.addAll(Arrays.asList(d));
            list.add(l);
        }

        return list;
    }

    // Getter
    public MainWindows getMainWindows() {
        return mainWindows;
    }

    public JPanel getTitlePanel() {
        return titlePanel;
    }

    public JPanel getControl() {
        return control;
    }

    public JLabel getMinimize() {
        return minimize;
    }

    public JLabel getMaximize() {
        return maximize;
    }

    public JLabel getClose() {
        return close;
    }

    public Icon getCloseB() {
        return closeB;
    }

    public Icon getCloseW() {
        return closeW;
    }

    public Icon getMaximizeB() {
        return maximizeB;
    }

    public Icon getMaximizeW() {
        return maximizeW;
    }

    public Icon getRestoreB() {
        return restoreB;
    }

    public Icon getRestoreW() {
        return restoreW;
    }

    public Icon getMiniB() {
        return miniB;
    }

    public Icon getMiniW() {
        return miniW;
    }

}
