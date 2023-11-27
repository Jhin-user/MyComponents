package MyComponents;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Jhin
 */
public class BUSMyTable {

    private MyTable myTable;

    public BUSMyTable(MyTable myTable) {
        this.myTable = myTable;
        events();
    }

    private void events() {
        myTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = myTable.getSelectedRow();
                
                if (selectedRow != -1) {
                    Object[] row = new Object[myTable.getColumnCount()];
                    
                    System.out.print("Rows: ");
                    for (int i = 0; i < myTable.getColumnCount(); i++) {
                        row[i] = myTable.getTableModel().getValueAt(selectedRow, i);
                        System.out.print(row[i] + ", ");
                    }
                    System.out.println("");
                    
                    myTable.selectedRow(selectedRow);
                }
            }
        });
    }

}
