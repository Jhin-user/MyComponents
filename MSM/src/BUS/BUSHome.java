package BUS;

import DAO.DAOItem;
import DTO.Item;
import DataList.ListItem;
import GUI.Home;
import Images.ImageSupport;
import Support.DataSupport;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author Jhin
 */
public class BUSHome {

    private final ImageIcon updateIcon = new ImageIcon(getClass().getResource("/Images/update.png"));
    private final ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/Images/delete.png"));

    private Home home;
    private JPopupMenu rightClick;
    private JMenuItem update, delete;

    private int selectedRow;

    public BUSHome(Home home) {
        this.home = home;
        initComponents();
        event();
    }

    private void initComponents() {
        rightClick = new JPopupMenu();

        update = new JMenuItem("Update", ImageSupport.getSizedIcon(updateIcon, 30, 30));
        delete = new JMenuItem("Delete", ImageSupport.getSizedIcon(deleteIcon, 30, 30));
        rightClick.add(update);
        rightClick.add(delete);
    }

    private void event() {
        rightClick();
        menuItem();
    }

    private void rightClick() {
        /* ---------- Right click table ---------- */
        home.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectedRow = home.getTable().rowAtPoint(e.getPoint());
                home.getTable().selectedRow(selectedRow);

                /* Chuột phải */
                if (SwingUtilities.isRightMouseButton(e)) {
                    /* home.getComponent() ~ home.getTable() */
                    rightClick.show(home.getTable(), e.getX(), e.getY());
                }
            }
        });
    }

    private void menuItem() {
        /* ---------- Items events ---------- */
        update.addActionListener((ActionEvent e) -> {
            System.out.println("[BUSHome]: Update " + selectedRow);
            home.getWindow().newAddress("Update");

            /* ---------- Get Item for update ---------- */
            // Row data
            if (selectedRow != -1) {
                String rowId = home.getTable().getTableModel().getValueAt(selectedRow, 0).toString();

                /* ---------- Tìm item có id để set itemUpdate ---------- */
                for (Item i : ListItem.getListItem()) {
                    if (i.getId().equals(rowId)) {
                        home.setItemUpdate(i);
                        break;
                    }
                }
            } else {
                home.setItemUpdate(null);
            }

            home.getWindow().newUpdate();
        });

        delete.addActionListener((ActionEvent e) -> {
            System.out.println("[BUSHome]: Delete " + selectedRow);
            String deleteId = home.getTable().getValueAt(selectedRow, 0).toString();

            UIManager.put("OptionPane.yesButtonText", "Có");
            UIManager.put("OptionPane.noButtonText", "Không");
            UIManager.put("OptionPane.cancelButtonText", "Hủy");

            System.out.print("[BUSHome]: Option: ");
            switch (JOptionPane.showConfirmDialog(null, "Xác nhận xóa item", "Xóa item?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.YES_NO_CANCEL_OPTION, ImageSupport.getSizedIcon(deleteIcon, 48, 48))) {
                case JOptionPane.YES_OPTION -> {
                    System.out.println("Yes");
                    ListItem.delete(deleteId);
                    home.getTable().setData(DataSupport.toObjectData(ListItem.getListItem()));
                    DAOItem.GetInstance().Delete(deleteId);
                }
                case JOptionPane.NO_OPTION -> {
                    System.out.println("No");
                }
                case JOptionPane.CANCEL_OPTION -> {
                    System.out.println("Cancel");
                }
                case JOptionPane.CLOSED_OPTION -> {
                    System.out.println("Close");
                }
                default ->
                    throw new AssertionError();
            }
        });
    }
}
