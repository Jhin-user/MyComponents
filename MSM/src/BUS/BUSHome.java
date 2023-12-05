package BUS;

import GUI.Home;
import Images.ImageSupport;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private final ImageIcon updateIcon = new ImageIcon(getClass().getResource("../Images/update.png"));
    private final ImageIcon deleteIcon = new ImageIcon(getClass().getResource("../Images/delete.png"));

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
        rightClick = new JPopupMenu("test");

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
        });

        delete.addActionListener((ActionEvent e) -> {
            System.out.println("[BUSHome]: Delete " + selectedRow);
        });
    }

}
