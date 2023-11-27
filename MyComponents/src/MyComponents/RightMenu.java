package MyComponents;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Jhin
 */
public class RightMenu extends JFrame {

    private final Image background = new ImageIcon(getClass().getResource("../Images/back 2.jpg")).getImage();

    private JPanel backgroundPanel;
    private JPanel rightMenu;

    public RightMenu() {
        setDefaultCloseOperation(3);
        setSize(1024, 720);
        setPreferredSize(new Dimension(1024, 750));
        setLocationRelativeTo(null);
        setTitle("My Components");
        setLayout(new BorderLayout(0, 0));
        setUndecorated(true);
        setVisible(true);

        backgroundPanel = new JPanel(new FlowLayout(3, 0, 0)) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
            }
        };
        backgroundPanel.setPreferredSize(new Dimension(getWidth() - 16, getHeight() - 40));
        backgroundPanel.setLayout(new BorderLayout(0, 0));
        add(backgroundPanel, "Center");

        rightMenu = new JPanel(new FlowLayout(3, 0, 3));
        rightMenu.setPreferredSize(new Dimension(0, 0));
        backgroundPanel.add(rightMenu, "East");

        JLabel[] lb = new JLabel[5];
        for (int i = 0; i < 5; i++) {
            lb[i] = new JLabel("   HEHE " + i);
            lb[i].setPreferredSize(new Dimension(300, 40));
            lb[i].setOpaque(true);
            lb[i].setBackground(Color.gray);
            lb[i].setForeground(Color.white);
            lb[i].setFont(new Font("Consolas", 1, 16));
            rightMenu.add(lb[i]);
        }

        JPanel south = new JPanel(new FlowLayout(FlowLayout.TRAILING, 0, 0));
        backgroundPanel.add(south, "South");

        JButton btn = new JButton("Show menu");
        btn.setPreferredSize(new Dimension(100, 40));
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Thread() {
                    @Override
                    @SuppressWarnings("SleepWhileInLoop")
                    public void run() {
                        if (rightMenu.getPreferredSize().width == 0) {
                            for (int i = 5; i <= 300; i += 5) {
                                rightMenu.setPreferredSize(new Dimension(i, 0));
                                backgroundPanel.revalidate();
                                try {
                                    Thread.sleep(6);
                                } catch (InterruptedException ex) {
                                }
                            }
                            return;
                        }
                        if (rightMenu.getPreferredSize().width > 0) {
                            for (int i = 295; i >= 0; i -= 5) {
                                rightMenu.setPreferredSize(new Dimension(i, 0));
                                backgroundPanel.revalidate();
                                try {
                                    Thread.sleep(6);
                                } catch (InterruptedException ex) {
                                }
                            }
                        }
                    }
                }.start();
            }
        });
        south.add(btn);

        JPanel center = new JPanel(new FlowLayout(3, 4, 3));
        backgroundPanel.add(center, "Center");

        JLabel[] centerLb = new JLabel[12];
        for (int i = 0; i < 12; i++) {
            centerLb[i] = new JLabel("  Label " + i);
            centerLb[i].setPreferredSize(new Dimension(150, 40));
            centerLb[i].setFont(new Font("Consolas", 1, 16));
            centerLb[i].setOpaque(true);
            centerLb[i].setBackground(Color.white);
            center.add(centerLb[i]);
        }

        revalidate();
    }

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            EventQueue.invokeLater(() -> {
                new RightMenu();
            });
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
        }
    }
}
