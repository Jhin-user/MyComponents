package MyComponents;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Jhin
 */
public class LeftMenu extends JFrame {

    private final Image background = new ImageIcon(getClass().getResource("../Images/back 2.jpg")).getImage();

    private JPanel leftMenu;

    public LeftMenu() {
        setDefaultCloseOperation(3);
        setSize(1024, 720);
        setPreferredSize(new Dimension(1024, 750));
        setLocationRelativeTo(null);
        setTitle("My Components");
        setLayout(null);
        setUndecorated(true);
        setVisible(true);

        leftMenu = new JPanel(new FlowLayout(3, 0, 3));
        leftMenu.setBounds(0, 0, 40, 720);
        leftMenu.setBackground(new Color(165, 255, 0, 100));
        leftMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                new Thread() {
                    @Override
                    @SuppressWarnings("SleepWhileInLoop")
                    public void run() {
                        for (int i = 45; i <= 250; i += 5) {
                            leftMenu.setBounds(0, 0, i, 720);
                            try {
                                Thread.sleep(6);
                            } catch (InterruptedException e) {
                            }
                        }
                    }
                }.start();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                new Thread() {
                    @Override
                    @SuppressWarnings("SleepWhileInLoop")
                    public void run() {
                        for (int i = 245; i >= 40; i -= 5) {
                            leftMenu.setBounds(0, 0, i, 720);
                            try {
                                Thread.sleep(6);
                            } catch (InterruptedException e) {
                            }
                        }
                    }
                }.start();
            }
        });
        add(leftMenu);

        ImageIcon img = new ImageIcon(getClass().getResource("../Images/find.png"));
        JPanel[] menuItem = new JPanel[4];
        JLabel[] menuIcon = new JLabel[4];
        JLabel[] menuText = new JLabel[4];
        for (int i = 0; i < 4; i++) {
            menuItem[i] = new JPanel(new FlowLayout(3, 0, 0));
            menuItem[i].setPreferredSize(new Dimension(250, 40));
            menuItem[i].setBackground(new Color(255, 165, 0, 100));
            leftMenu.add(menuItem[i]);

            menuIcon[i] = new JLabel(img);
            menuIcon[i].setPreferredSize(new Dimension(40, 40));
            menuItem[i].add(menuIcon[i]);

            menuText[i] = new JLabel("label " + i, JLabel.CENTER);
            menuText[i].setPreferredSize(new Dimension(210, 40));
            menuItem[i].add(menuText[i]);
        }

        JPanel mainPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
            }
        };
        mainPanel.setBounds(0, 0, 1024, 720);
        add(mainPanel);

        revalidate();
    }

//    @SuppressWarnings("ResultOfObjectAllocationIgnored")
//    public static void main(String[] args) {
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//            EventQueue.invokeLater(() -> {
//                new LeftMenu();
//            });
//        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
//        }
//    }

}
