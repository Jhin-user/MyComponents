package BUS;

import GUI.Home;
import Images.ImageSupport;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

/**
 *
 * @author Jhin
 */
public class BUSHome {

    private final ImageIcon[] popupMenuIcon = new ImageIcon[]{
        new ImageIcon(getClass().getResource("../Images/home.png")),
        new ImageIcon(getClass().getResource("../Images/search.png"))
    };
    private final String[] itemNames = new String[]{"Update", "Delete"};

    private Home home;
    private JPopupMenu popupMenu;
    private JMenuItem i1, i2;
    private JMenuItem[] menuItem;
    private int selectedRow;

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public BUSHome(Home home) {
        this.home = home;
        initComponents();
        events();
    }

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    private void initComponents() {
        popupMenu = new JPopupMenu();
        menuItem = new JMenuItem[itemNames.length];
    }

    private void events() {
        home.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                selectedRow = home.getTable().rowAtPoint(e.getPoint());
                home.getTable().selectedRow(selectedRow);
                if (SwingUtilities.isRightMouseButton(e)) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    if (selectedRow != -1) {
                        Object[] row = new Object[home.getTable().getColumnCount()];

//                        System.out.print("Rows: ");
//                        for (int i = 0; i < home.getTable().getColumnCount(); i++) {
//                            row[i] = home.getTable().getTableModel().getValueAt(selectedRow, i);
//                            System.out.print(row[i] + ", ");
//                        }
//                        System.out.println("");
                    }
                }

            }
        });

        for (int i = 0; i < itemNames.length; i++) {
            menuItem[i] = new JMenuItem(itemNames[i], ImageSupport.getSizedIcon(popupMenuIcon[i], 25, 25));
            menuItem[i].addActionListener((ActionEvent e) -> {
                switch (e.getActionCommand()) {
                    case "Update" ->
                        System.out.println("[BUSHome]: Update " + selectedRow);
                    case "Delete" ->
                        System.out.println("[BUSHome]: Delete " + selectedRow);
                    default ->
                        throw new AssertionError();
                }
            });
            popupMenu.add(menuItem[i]);
        }
    }

}
