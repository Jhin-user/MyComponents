package GUISupport;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Jhin
 */
public class MyTransparencyTable extends JTable {

    private DefaultTableCellRenderer headerRenderer, rowRenderer;

    public MyTransparencyTable(Object[][] rowData, Object[] columnNames, DefaultTableCellRenderer headerRenderer, DefaultTableCellRenderer rowRenderer) {
        super(rowData, columnNames);
        UIManager.put("TableHeader.background", new Color(0, 0, 0, 0));
        this.headerRenderer = headerRenderer;
        this.rowRenderer = rowRenderer;
        config();
    }

    private void config() {
        super.setOpaque(false);

        super.getTableHeader().setDefaultRenderer(headerRenderer);
        for (int i = 0; i < getColumnCount(); i++) {
            super.getColumnModel().getColumn(i).setCellRenderer(rowRenderer);
        }
    }

    // Method setter
    public void setHeaderBackground(Color c) {
        headerRenderer.setBackground(c);
    }

    public void setHeaderForeground(Color c) {
        headerRenderer.setForeground(c);
    }

    public void setHeaderFont(Font font) {
        headerRenderer.setFont(font);
    }

    public void setHeaderHeight(int height) {
        headerRenderer.setPreferredSize(new Dimension(headerRenderer.getWidth(), height));
    }

    public void setRowsBackground(Color c) {
        rowRenderer.setBackground(c);
    }
    
    public void setRowsForeground(Color c) {
        rowRenderer.setForeground(c)
    }

    // Setter
    public void setHeaderRenderer(DefaultTableCellRenderer headerRenderer) {
        this.headerRenderer = headerRenderer;
    }

    public void setRowRenderer(DefaultTableCellRenderer rowRenderer) {
        this.rowRenderer = rowRenderer;
    }

}
