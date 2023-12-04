package MyComponents;

import Images.ImageSupport;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.time.LocalTime;
import javax.swing.ImageIcon;
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
    private ImageIcon dayIcon, nightIcon;

    public RealTimePanel() {
        initComponents();
    }

    public RealTimePanel(ImageIcon dayIcon, ImageIcon nightIcon) {
        this.dayIcon = dayIcon;
        this.nightIcon = nightIcon;
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
        if (dayIcon != null && nightIcon != null) {
            if (h < 12) {
                clockLabel.setIcon(ImageSupport.getSizedIcon(dayIcon, 40, 40));
                repaint();
            } else {
                clockLabel.setIcon(ImageSupport.getSizedIcon(nightIcon, 40, 40));
                repaint();
            }
        }
        add(clockLabel, "Center");

        new Timer(1000, (ActionEvent e) -> {
            localTime = LocalTime.now();
            h = localTime.getHour();
            m = localTime.getMinute();
            s = localTime.getSecond();
            clockLabel.setText(m < 10 ? (s < 10 ? h + ":0" + m + ":0" + s : h + ":0" + m + ":" + s) : (s < 10 ? h + ":" + m + ":0" + s : h + ":" + m + ":" + s));
            if (dayIcon != null && nightIcon != null) {
                if (h < 12) {
                    clockLabel.setIcon(ImageSupport.getSizedIcon(dayIcon, 40, 40));
                    repaint();
                } else {
                    clockLabel.setIcon(ImageSupport.getSizedIcon(nightIcon, 40, 40));
                    repaint();
                }
            }
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

    public void setClockIcon(ImageIcon dayIcon, ImageIcon nightIcon) {
        if (LocalTime.now().getHour() < 12) {
            this.dayIcon = ImageSupport.getSizedIcon(dayIcon, 40, 40);
            clockLabel.setIcon(dayIcon);
        } else {
            this.nightIcon = ImageSupport.getSizedIcon(nightIcon, 40, 40);
            clockLabel.setIcon(nightIcon);
        }
    }

    // Getter
    public LocalTime getLocalTime() {
        return localTime;
    }

}
