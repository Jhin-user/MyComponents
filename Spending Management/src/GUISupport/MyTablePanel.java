package GUISupport;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

/**
 *
 * @author Jhin
 */
public class MyTablePanel extends JPanel {
    
    /**
     * Chức năng
     * 1. Khởi tạo với Data và ColumnNames
     * 2. Thay đối màu nên, màu chữ, font chữ của header
     * 3. Thay đối màu nên, màu chữ, font chữ của data
     * 4. Thêm menu khi click phải dòng Data
     */

    private ArrayList<ArrayList<Object>> rowData;
    private Object[] columnNames;
    private int row;
    private int column;

    private JPanel headerPanel;
    private JLabel[] headerItem;
    private JPanel rowDataPanel;
    private JPanel[] rows;
    private JLabel[][] rowItem;
    private Color headerBackground, headerForeground;
    private Font headerFont;
    private Color rowBackground, rowForeground;
    private Font rowFont;

    private Color selectedColor;
    private int selectRow;
    private JMenuItem[] menuItem;

    public MyTablePanel(ArrayList<ArrayList<Object>> rowData, Object[] columnNames) {
        this.rowData = rowData;
        this.columnNames = columnNames;
        this.row = rowData.size();
        this.column = columnNames.length;
        this.headerBackground = new Color(255, 255, 255, 200);
        this.headerFont = new Font("Monospaced", Font.BOLD, 18);
        this.headerForeground = Color.black;
        this.rowBackground = new Color(175, 175, 175, 128);
        this.rowFont = new Font("Consolas", Font.PLAIN, 16);
        this.rowForeground = Color.white;
        this.selectedColor = new Color(255, 165, 0, 128);
        this.selectRow = -1;
        this.menuItem = null;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(0, 0));
        setOpaque(false);

        initHeader();
        initRows();
        rowEvent();
    }

    private void initHeader() {
        headerPanel = new JPanel(new GridLayout(1, column, -1, 0));
        headerPanel.setBackground(headerBackground);
        add(headerPanel, "North");

        headerItem = new JLabel[column];
        for (int i = 0; i < column; i++) {
            headerItem[i] = new JLabel(columnNames[i].toString());
            headerItem[i].setFont(headerFont);
            headerItem[i].setForeground(headerForeground);
            headerItem[i].setBorder(new LineBorder(Color.black, 1));
            headerPanel.add(headerItem[i]);
        }
    }

    private void initRows() {
        rowDataPanel = new JPanel(new GridLayout(row, 1, -1, 0));
        rowDataPanel.setOpaque(false);
        add(rowDataPanel, "Center");

        rows = new JPanel[row];
        rowItem = new JLabel[row][column];

        for (int i = 0; i < row; i++) {
            rows[i] = new JPanel(new GridLayout(1, column, 0, 0));
            rows[i].setBackground(rowBackground);
            rowDataPanel.add(rows[i]);

            rowItem[i] = new JLabel[column];
            for (int j = 0; j < column; j++) {
                rowItem[i][j] = new JLabel(rowData.get(i).get(j).toString());
                rowItem[i][j].setFont(rowFont);
                rowItem[i][j].setForeground(rowForeground);
                rowItem[i][j].setBorder(new MatteBorder(0, 0, 1, 1, Color.lightGray));
                rows[i].add(rowItem[i][j]);
            }
        }
    }

    private void rowEvent() {
        for (int i = 0; i < row; i++) {
            int t = i;
            rows[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    for (JPanel p : rows) {
                        p.setBackground(rowBackground);
                    }
                    rows[t].setBackground(selectedColor);
                    selectRow = t;
                    repaint();
                    // Kiểm tra nếu là sự kiện chuột phải
                    if (SwingUtilities.isRightMouseButton(e) && menuItem != null) {
                        showPopupMenu(e.getComponent(), e.getX(), e.getY());
                    }
                }
            });
        }
    }

    /* ---------- Method ---------- */
    // Method
    private void showPopupMenu(Component component, int x, int y) {
        JPopupMenu popupMenu = new JPopupMenu();

        for (JMenuItem item : menuItem) {
            popupMenu.add(item);
        }

        popupMenu.show(component, x, y);
    }

    // Method Get
    // Method Set
    public void setHeaderHeight(int height) {
        for (JLabel header : headerItem) {
            header.setPreferredSize(new Dimension(0, height));
        }
    }

    public void setRowHeight(int height) {
        for (JLabel[] r : rowItem) {
            r[0].setPreferredSize(new Dimension(0, height));
        }
    }

    /* Getter, Setter */
    // Getter
    public ArrayList<ArrayList<Object>> getRowData() {
        return rowData;
    }

    public Object[] getColumnNames() {
        return columnNames;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getSelectRow() {
        return selectRow;
    }

    // Setter
    public void setRowData(ArrayList<ArrayList<Object>> rowData) {
        this.rowData = rowData;
    }

    public void setColumnNames(Object[] columnNames) {
        this.columnNames = columnNames;
    }

    public void setHeaderBackground(Color headerBackground) {
        Color oldBackground = this.headerBackground;
        if (oldBackground != headerBackground) {
            this.headerBackground = headerBackground;
            for (int i = 0; i < column; i++) {
                headerItem[i].setForeground(this.headerBackground);
            }
        }
    }

    public void setHeaderFont(Font font) {
        Font oldFont = headerFont;
        if (font != oldFont) {
            this.headerFont = font;
            for (int i = 0; i < column; i++) {
                headerItem[i].setFont(headerFont);
            }
            revalidate();
            repaint();
        }
    }

    public void setHeaderForeground(Color headerForeground) {
        Color oldForeground = this.headerForeground;
        if (oldForeground != headerForeground) {
            this.headerForeground = headerForeground;
            for (int i = 0; i < column; i++) {
                headerItem[i].setForeground(this.headerForeground);
            }
        }
    }

    public void setMenuItem(JMenuItem[] menuItem) {
        this.menuItem = menuItem;
    }

}
