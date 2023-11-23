/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author Jhin
 */
import java.awt.Component;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RightClickExample {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            EventQueue.invokeLater(() -> {
                createAndShowGUI();
            });
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
        }
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Right-Click Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Right-click me!");

        // Sử dụng MouseAdapter để bắt sự kiện chuột
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Kiểm tra nếu là sự kiện chuột phải
                if (SwingUtilities.isRightMouseButton(e)) {
                    showPopupMenu(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        panel.add(label);
        frame.getContentPane().add(panel);

        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void showPopupMenu(Component component, int x, int y) {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem menuItem = new JMenuItem("Right-clicked!");
        JMenuItem menuItem1 = new JMenuItem("Right-clicked! 1");
        JMenuItem menuItem2 = new JMenuItem("Right-clicked! 2");
        JMenuItem menuItem3 = new JMenuItem("Right-clicked! 3");
        JMenuItem menuItem4 = new JMenuItem("Right-clicked! 4");
        JMenuItem menuItem5 = new JMenuItem("Right-clicked! 5");
        popupMenu.add(menuItem);
        popupMenu.add(menuItem1);
        popupMenu.add(menuItem2);
        popupMenu.add(menuItem3);
        popupMenu.add(menuItem4);
        popupMenu.add(menuItem5);

        popupMenu.show(component, x, y);
    }
}

