package MyComponents;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Jhin
 */
public class Test extends JFrame {

    private CardLayout cardLayout;

    public Test() {
        cardLayout = new CardLayout(0, 0);

        JPanel main = new JPanel(cardLayout);
        main.setPreferredSize(new Dimension(700, 606));
        add(main);

        JPanel p1 = new JPanel();
        p1.setBackground(Color.red);

        JPanel p2 = new JPanel();
        p2.setBackground(Color.yellow);

        JPanel p3 = new JPanel();
        p3.setBackground(Color.green);

        main.add(p1, "p1");
        main.add(p2, "p2");
        main.add(p3, "p3");

        JPanel btn = new JPanel(new FlowLayout(3, 0, 0));
        btn.setPreferredSize(new Dimension(150, 150));
        add(btn);

        JButton b1 = new JButton("B1");
        b1.setPreferredSize(new Dimension(150, 50));
        b1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("b1");
                cardLayout.show(main, "p1");
            }
        });
        btn.add(b1);

        JButton b2 = new JButton("B2");
        b2.setPreferredSize(new Dimension(150, 50));
        b2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("b2");
                cardLayout.show(main, "p2");
            }
        });
        btn.add(b2);

        JButton b3 = new JButton("B3");
        b3.setPreferredSize(new Dimension(150, 50));
        b3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("b3");
                cardLayout.show(main, "p3");
            }
        });
        btn.add(b3);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 720);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(3, 0, 0));
        setVisible(true);
    }

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public static void main(String[] args) {
        new Test();
    }
}
