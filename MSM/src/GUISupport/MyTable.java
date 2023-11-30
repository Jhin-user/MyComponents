package GUISupport;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jhin
 */
public class MyTable extends JTable {

    private DefaultTableModel tableModel;
    private DefaultTableCellRenderer headerRenderer, tableRenderer, selectedRowRenderer;
    private Color selectedRowColor;

    public MyTable() {
        /* TableModel */
        tableModel = new DefaultTableModel();
        setModel(tableModel);

        /* TableHeader */
        headerRenderer = new DefaultTableCellRenderer();
        getTableHeader().setDefaultRenderer(headerRenderer);

        /* Table */
        tableRenderer = new DefaultTableCellRenderer();
        for (int i = 0; i < getColumnCount(); i++) {
            super.getColumnModel().getColumn(i).setCellRenderer(tableRenderer);
        }

        /* Selected Row */
        selectedRowRenderer = new DefaultTableCellRenderer();
        selectedRowColor = new Color(200, 200, 200, 150);

        /* Chỉ cho phép chọn một dòng */
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    /* ---------- Set Header ---------- */
    public void setHeaders(String[] headers) {
        tableModel.setColumnIdentifiers(headers);
        setModel(tableModel);
    }

    public void setHeadersProperties(Color background, Color foreground, Font font, int headerHeight) {
        headerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                component.setBackground(background);
                component.setForeground(foreground);
                component.setFont(font);
                component.setPreferredSize(new Dimension(getWidth(), headerHeight));

                /* Ngăn cách các ô trong header */
                setBorder(new MatteBorder(0, 0, 0, 1, Color.black));

                return component;
            }
        };
        /* -------------------- SetReisze -------------------- */
        getTableHeader().getColumnModel().getColumn(0).setResizable(true);
        /* --------------------------------------------------- */
        getTableHeader().setDefaultRenderer(headerRenderer);
        getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                repaint();
            }
        });
    }

    /* ---------- Table Model ---------- */
    public void setData(Object[][] data) {
        tableModel.setRowCount(0);
        for (Object[] obj : data) {
            tableModel.addRow(obj);
        }
    }

    public void setTableProperties(Color background, Color foreground, Font font, int rowHeight) {
        tableRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                component.setBackground(background);
                component.setForeground(foreground);
                component.setFont(font);

                return component;
            }
        };
        for (int i = 0; i < getColumnCount(); i++) {
            super.getColumnModel().getColumn(i).setCellRenderer(tableRenderer);
        }
        setRowHeight(rowHeight);
    }

    /* ---------- Selected row properties ---------- */
    public void setSelectedRowColor(Color selectedRowColor) {
        this.selectedRowColor = selectedRowColor;
    }

    public void selectedRow(int selectedRow) {
        selectedRowRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (selectedRow == row) {
                    component.setBackground(selectedRowColor);
                } else {
                    component.setBackground(tableRenderer.getBackground());
                }
                component.setForeground(tableRenderer.getForeground());
                component.setFont(tableRenderer.getFont());

                /* Ngăn cách các ô trong header */
                setBorder(new MatteBorder(0, 0, 0, 1, Color.black));

                return component;
            }
        };
        for (int i = 0; i < getColumnCount(); i++) {
            super.getColumnModel().getColumn(i).setCellRenderer(selectedRowRenderer);
        }
        setRowHeight(rowHeight);
    }

    // Getter
    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public DefaultTableCellRenderer getHeaderRenderer() {
        return headerRenderer;
    }

}
