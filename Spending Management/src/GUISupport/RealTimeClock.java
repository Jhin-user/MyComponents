package GUISupport;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.time.LocalTime;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Jhin
 */
public class RealTimeClock extends JPanel {

    private LocalTime time;

    private Dimension preferredSize;
    private JLabel hour, minute, second;

    public RealTimeClock(Dimension preferredSize) {
        this.preferredSize = preferredSize;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(0, 0));
        setPreferredSize(preferredSize);

        /* Border - Margin */
        JLabel e = new JLabel(), w = new JLabel(), s = new JLabel(), n = new JLabel();
        e.setPreferredSize(new Dimension(preferredSize.width / 12, 0));
        w.setPreferredSize(new Dimension(preferredSize.width / 12, 0));
        s.setPreferredSize(new Dimension(0, preferredSize.height / 16));
        n.setPreferredSize(new Dimension(0, preferredSize.height / 16));
        add(e, "East");
        add(w, "West");
        add(s, "South");
        add(n, "North");
        /* Border */

        // Get time
        time = LocalTime.now();

        hour = new JLabel(time.toString().split(":")[0], JLabel.CENTER);
        minute = new JLabel(time.toString().split(":")[1], JLabel.CENTER);
        int sec = Integer.parseInt(time.toString().split(":")[2].substring(0, 2));
        second = new JLabel(sec < 10 ? "0" + sec : sec + "", JLabel.CENTER);

        // Render
        int width = preferredSize.width - preferredSize.width / 6, height = preferredSize.height - preferredSize.height / 8;

        JPanel clock = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        clock.setPreferredSize(new Dimension(width, height));
        clock.setOpaque(false);
        add(clock, "Center");

        JLabel colon1 = new JLabel(":", JLabel.CENTER), colon2 = new JLabel(":", JLabel.CENTER);
        
        hour.setPreferredSize(new Dimension((width - width / 10 * 2 - 2) / 3, height));
        colon1.setPreferredSize(new Dimension(width / 10, height));
        minute.setPreferredSize(new Dimension((width - width / 10 * 2 - 2) / 3, height));
        colon2.setPreferredSize(new Dimension(width / 10, height));
        second.setPreferredSize(new Dimension((width - width / 10 * 2 - 2) / 3, height));
        
        Font f = new Font("Consolas", Font.BOLD, (width - width / 10 * 2 - 2) / 6);
        hour.setFont(f);
        colon1.setFont(f);
        minute.setFont(f);
        colon2.setFont(f);
        second.setFont(f);
        
        clock.add(hour);
        clock.add(colon1);
        clock.add(minute);
        clock.add(colon2);
        clock.add(second);

        new Timer(1000, (ActionEvent event) -> {
            time = LocalTime.now();
            hour.setText(time.toString().split(":")[0]);
            minute.setText(time.toString().split(":")[1]);
            int currentSecond = Integer.parseInt(time.toString().split(":")[2].substring(0, 2));
            second.setText(currentSecond < 10 ? "0" + currentSecond : currentSecond + "");
        }).start();
    }
}
