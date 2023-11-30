package GUI;

import BUS.BUSHome;
import GUISupport.Card;
import GUISupport.MyTable;
import GUISupport.RadiusPanel;
import Images.ImageSupport;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.AdjustmentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

/**
 *
 * @author Jhin
 */
public class Home extends Card {

    private final ImageIcon backIcon = new ImageIcon(getClass().getResource("../Images/back.png"));
    private final ImageIcon searchIcon = new ImageIcon(getClass().getResource("../Images/search.png"));
    private final Color[] searchFieldColor = new Color[]{
        new Color(7, 175, 230, 200),
        new Color(75, 187, 146, 200),
        new Color(135, 197, 72, 200)
    };

    private Window window;
    private MouseMotionAdapter repaint;

    private JLabel backLabel, searchLabel;

    private MyTable table;

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public Home(String cardName, Window window) {
        super(cardName);
        this.window = window;
        this.repaint = new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                repaint();
            }
        };
        initComponents();
        new BUSHome(this);
    }

    private void initComponents() {
        setLayout(new BorderLayout(0, 5));
        setOpaque(false);
        
        JLabel east = new JLabel(), west = new JLabel(), south = new JLabel();
        east.setPreferredSize(new Dimension(10, 0));
        west.setPreferredSize(new Dimension(10, 0));
        south.setPreferredSize(new Dimension(0, 10));
        add(east, "East");
        add(west, "West");
        add(south, "South");

        /* Search */
        JPanel searchPanel = new JPanel(new BorderLayout(5, 0));
        searchPanel.setOpaque(false);
        add(searchPanel, "North");

        backLabel = new JLabel(ImageSupport.getSizedIcon(backIcon, 35, 35));
        backLabel.setPreferredSize(new Dimension(40, 40));
        searchPanel.add(backLabel, "West");

        RadiusPanel search = new RadiusPanel(45, 3, new Color(200, 200, 200, 100), searchFieldColor);
        searchPanel.add(search, "Center");

        searchLabel = new JLabel(ImageSupport.getSizedIcon(searchIcon, 35, 35));
        searchLabel.setPreferredSize(new Dimension(40, 40));
        searchPanel.add(searchLabel, "East");

        /* Table */
        JScrollPane jsp = new JScrollPane();
        jsp.getVerticalScrollBar().addAdjustmentListener((AdjustmentEvent e) -> {
            e.getAdjustable().setUnitIncrement(10);
        });
        jsp.setOpaque(false);
        jsp.getViewport().setOpaque(false);
        add(jsp, "Center");

        UIManager.put("TableHeader.background", new Color(0, 0, 0, 0));
        table = new MyTable();
        table.setHeaders(new String[]{"Col 1", "Col 2", "Col 3"});
        table.setOpaque(false);
        table.setHeadersProperties(new Color(0, 175, 235, 150), Color.white, new Font("Monospaced", 1, 20), 30);
        table.setTableProperties(new Color(100, 100, 100, 150), Color.white, new Font("Consolas", 0, 18), 50);
        String[][] dataTest = new String[50][3];
        /* ---------- Data Test ---------- */
        for (int i = 0; i < 50; i++) {
            String[] s = new String[3];
            for (int j = 0; j < 3; j++) {
                s[j] = "Row " + i + ", Col " + j;
            }
            dataTest[i] = s;
        }
        /* ------------------------------- */
        table.setData(dataTest);
        jsp.setViewportView(table);

        table.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                window.getCard()[0].repaint();
            }
        });
        table.getTableHeader().addMouseMotionListener(repaint);
        table.addMouseMotionListener(repaint);
    }

    public MyTable getTable() {
        return table;
    }

}
