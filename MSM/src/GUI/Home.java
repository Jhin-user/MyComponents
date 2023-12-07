package GUI;

import BUS.BUSHome;
import DTO.Item;
import DataList.ListItem;
import GUISupport.MyTable;
import Support.DataSupport;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.AdjustmentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

/**
 *
 * @author Jhin
 */
public class Home extends JPanel {

    private Window window;
    private Item itemUpdate;
    private MouseMotionAdapter repaint;

    private MyTable table;

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public Home(Window window) {
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
        setLayout(new BorderLayout(0, 0));
        setOpaque(false);

        add(new JLabel() {
            {
                setPreferredSize(new Dimension(10, 0));
            }
        }, "East");
        add(new JLabel() {
            {
                setPreferredSize(new Dimension(10, 0));
            }
        }, "West");
        add(new JLabel() {
            {
                setPreferredSize(new Dimension(0, 10));
            }
        }, "South");
        add(new JLabel() {
            {
                setPreferredSize(new Dimension(0, 10));
            }
        }, "North");

        JScrollPane jsp = new JScrollPane();
        jsp.getVerticalScrollBar().addAdjustmentListener((AdjustmentEvent e) -> {
            e.getAdjustable().setUnitIncrement(10);
        });
        jsp.setOpaque(false);
        jsp.getViewport().setOpaque(false);
        add(jsp, "Center");

        UIManager.put("TableHeader.background", new Color(0, 0, 0, 0));
        table = new MyTable();
        table.setHeaders(new String[]{"ID", "Item", "Time", "Count", "Price"});
        table.setOpaque(false);
        table.setHeadersProperties(new Color(0, 175, 235, 150), Color.white, new Font("Monospaced", 1, 20), 30);
        table.setTableProperties(new Color(100, 100, 100, 150), Color.white, new Font("Consolas", 0, 18), 50);
        table.setData(DataSupport.toObjectData(ListItem.getListItem()));
        table.setSelectedRowColor(new Color(255, 192, 203, 150));
        jsp.setViewportView(table);

        /* width */
        table.getColumnModel().getColumn(0).setPreferredWidth(table.getPreferredSize().width / 5 - 80);
        table.getColumnModel().getColumn(1).setPreferredWidth(table.getPreferredSize().width / 5 + 60);
        table.getColumnModel().getColumn(2).setPreferredWidth(table.getPreferredSize().width / 5 + 80);
        table.getColumnModel().getColumn(3).setPreferredWidth(table.getPreferredSize().width / 5 - 40);
        table.getColumnModel().getColumn(4).setPreferredWidth(table.getPreferredSize().width / 5 - 20);

        table.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                window.getHome().repaint();
            }
        });
        table.getTableHeader().addMouseMotionListener(repaint);
        table.addMouseMotionListener(repaint);
    }

    // Getter
    public Window getWindow() {
        return window;
    }

    public Item getItemUpdate() {
        return itemUpdate;
    }

    public MyTable getTable() {
        return table;
    }

    // Setter
    public void setItemUpdate(Item itemUpdate) {
        this.itemUpdate = itemUpdate;
    }

}
