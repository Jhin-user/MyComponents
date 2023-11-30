package GUISupport;

import java.awt.BorderLayout;
import java.awt.Color;
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
public class RealTimePanel extends JPanel {

    private JLabel clockLabel;
    private LocalTime localTime;
    private int h, m, s;

    public RealTimePanel() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        localTime = LocalTime.now();
        h = localTime.getHour();
        m = localTime.getMinute();
        s = localTime.getSecond();

        clockLabel = new JLabel(m < 10 ? (s < 10 ? h + ":0" + m + ":0" + s : h + ":0" + m + ":" + s) : (s < 10 ? h + ":" + m + ":0" + s : h + ":" + m + ":" + s), 0);
        clockLabel.setFont(new Font("SegueUI", 1, 16));
        add(clockLabel, "Center");

        new Timer(1000, (ActionEvent e) -> {
            localTime = LocalTime.now();
            h = localTime.getHour();
            m = localTime.getMinute();
            s = localTime.getSecond();
            clockLabel.setText(m < 10 ? (s < 10 ? h + ":0" + m + ":0" + s : h + ":0" + m + ":" + s) : (s < 10 ? h + ":" + m + ":0" + s : h + ":" + m + ":" + s));
            revalidate();
            repaint();
        }).start();
    }

    // Method
    public void setClockBackground(Color background) {
        setBackground(background);
    }

    public void setClockFont(Font clockFont) {
        clockLabel.setFont(clockFont);
    }

    public void setClockForeground(Color foreground) {
        clockLabel.setForeground(foreground);
    }

    // Getter
    public LocalTime getLocalTime() {
        return localTime;
    }

}
