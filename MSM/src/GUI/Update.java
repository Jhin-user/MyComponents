package GUI;

import BUS.BUSUpdate;
import DTO.Item;
import GUISupport.RadiusPanel;
import Images.ImageSupport;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.util.stream.IntStream;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author Jhin
 */
public class Update extends JPanel {

    private final Font labelFont = new Font("Monospaced", 1, 20);
    private final Font fieldFont = new Font("Monospaced", 0, 18);
    private final ImageIcon updateIcon = new ImageIcon(getClass().getResource("/Images/update.png"));

    private Window window;
    private Item itemUpdate;

    private JComboBox<Integer> dayChooser, yearChooser;
    private JComboBox<String> monthChooser;
    private JLabel errorLabel;
    private JTextField item, count, price, thousand;
    private JCheckBox number, kg;
    private RadiusPanel updateBorder;
    private JLabel update;

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public Update(Window window) {
        this.window = window;
        this.itemUpdate = window.getHome().getItemUpdate();
        initComponent();
        new BUSUpdate(this);
    }

    private void initComponent() {
        setLayout(new BorderLayout(0, 10));
        setOpaque(false);

        north();
        center();
        south();
    }

    public void north() {
        JPanel north = new JPanel(new FlowLayout(0, 0, 5));
        north.setPreferredSize(new Dimension(0, 50));
        north.setOpaque(false);
        add(north, "North");

        north.add(new JLabel() {
            {
                setPreferredSize(new Dimension(50, 40));
            }
        });

        JLabel dayLabel = new JLabel("Ngày:");
        dayLabel.setPreferredSize(new Dimension(75, 40));
        dayLabel.setFont(labelFont);
        dayLabel.setForeground(Color.white);
        north.add(dayLabel);

        dayChooser = new JComboBox<>(toIntegerArray(LocalDate.now().lengthOfMonth()));
        dayChooser.setPreferredSize(new Dimension(60, 40));
        dayChooser.setFont(labelFont);
        dayChooser.setOpaque(false);
        dayChooser.setSelectedIndex(itemUpdate.getDateTime().getDayOfMonth() - 1);
        north.add(dayChooser);

        north.add(new JLabel() {
            {
                setPreferredSize(new Dimension(50, 40));
            }
        });

        JLabel monthLabel = new JLabel("Tháng:");
        monthLabel.setPreferredSize(new Dimension(100, 40));
        monthLabel.setFont(labelFont);
        monthLabel.setForeground(Color.white);
        north.add(monthLabel);

        monthChooser = new JComboBox<>(new String[]{"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"});
        monthChooser.setPreferredSize(new Dimension(150, 40));
        monthChooser.setFont(labelFont);
        monthChooser.setOpaque(false);
        monthChooser.setSelectedIndex(itemUpdate.getDateTime().getMonthValue() - 1);
        north.add(monthChooser);

        north.add(new JLabel() {
            {
                setPreferredSize(new Dimension(75, 40));
            }
        });

        JLabel yearLabel = new JLabel("Năm:");
        yearLabel.setPreferredSize(new Dimension(75, 40));
        yearLabel.setFont(labelFont);
        yearLabel.setForeground(Color.white);
        north.add(yearLabel);

        yearChooser = new JComboBox<>(toIntegerArray(2000, 2200));
        yearChooser.setPreferredSize(new Dimension(150, 40));
        yearChooser.setFont(labelFont);
        yearChooser.setOpaque(false);
        yearChooser.setSelectedItem(itemUpdate.getDateTime().getYear());
        north.add(yearChooser);
    }

    public void center() {
        JPanel center = new JPanel(new GridLayout(6, 1));
        center.setOpaque(false);
        add(center, "Center");

        /* Error */
        JPanel errorPanel = new JPanel(new FlowLayout(3, 0, 0));
        errorPanel.setOpaque(false);
        center.add(errorPanel);
        
        errorPanel.add(new JLabel() {
            {
                setPreferredSize(new Dimension(40, 50));
            }
        });
        errorLabel = new JLabel();
        errorLabel.setFont(new Font("Monospaced", 1, 18));
        errorLabel.setForeground(new Color(255, 0, 0, 255));
        errorPanel.add(errorLabel);

        /* Item */
        JPanel itemPanel = new JPanel(new FlowLayout(3, 0, 0));
        itemPanel.setOpaque(false);
        center.add(itemPanel);

        itemPanel.add(new JLabel() {
            {
                setPreferredSize(new Dimension(40, 50));
            }
        });

        JLabel itemLB = new JLabel("Item");
        itemLB.setPreferredSize(new Dimension(100, 50));
        itemLB.setFont(labelFont);
        itemLB.setForeground(Color.white);
        itemPanel.add(itemLB);

        RadiusPanel itemBorder = new RadiusPanel(45, 3, new Color(200, 200, 200, 75));
        itemBorder.setPreferredSize(new Dimension(400, 50));
        itemBorder.setLayout(new FlowLayout(3, 25, 5));
        itemPanel.add(itemBorder);

        item = new JTextField(itemUpdate.getItem());
        item.setPreferredSize(new Dimension(300, 40));
        item.setBorder(null);
        item.setOpaque(false);
        item.setFont(fieldFont);
        item.setForeground(Color.white);
        itemBorder.add(item);

        /* Count */
        JPanel countPanel = new JPanel(new FlowLayout(3, 0, 0));
        countPanel.setOpaque(false);
        center.add(countPanel);

        countPanel.add(new JLabel() {
            {
                setPreferredSize(new Dimension(40, 50));
            }
        });

        JLabel countLb = new JLabel("Count");
        countLb.setPreferredSize(new Dimension(100, 50));
        countLb.setFont(labelFont);
        countLb.setForeground(Color.white);
        countPanel.add(countLb);

        RadiusPanel countBorder = new RadiusPanel(45, 3, new Color(200, 200, 200, 75));
        countBorder.setPreferredSize(new Dimension(400, 50));
        countBorder.setLayout(new FlowLayout(3, 25, 5));
        countPanel.add(countBorder);

        count = new JTextField(itemUpdate.getCount() + "");
        count.setPreferredSize(new Dimension(300, 40));
        count.setBorder(null);
        count.setOpaque(false);
        count.setFont(fieldFont);
        count.setForeground(Color.white);
        countBorder.add(count);

        countPanel.add(new JLabel() {
            {
                setPreferredSize(new Dimension(20, 50));
            }
        });

        number = new JCheckBox("Item(s)");
        number.setPreferredSize(new Dimension(120, 50));
        number.setFont(labelFont);
        number.setForeground(Color.white);
        number.setOpaque(false);
        countPanel.add(number);

        countPanel.add(new JLabel() {
            {
                setPreferredSize(new Dimension(20, 50));
            }
        });

        kg = new JCheckBox("kg");
        kg.setPreferredSize(new Dimension(120, 50));
        kg.setFont(labelFont);
        kg.setForeground(Color.white);
        kg.setOpaque(false);
        countPanel.add(kg);

        if (itemUpdate.isIsItem()) {
            number.setSelected(true);
        } else {
            kg.setSelected(true);
        }

        /* Price */
        JPanel pricePanel = new JPanel(new FlowLayout(3, 0, 0));
        pricePanel.setOpaque(false);
        center.add(pricePanel);

        pricePanel.add(new JLabel() {
            {
                setPreferredSize(new Dimension(40, 50));
            }
        });

        JLabel priceLb = new JLabel("Price");
        priceLb.setPreferredSize(new Dimension(100, 50));
        priceLb.setFont(labelFont);
        priceLb.setForeground(Color.white);
        pricePanel.add(priceLb);

        RadiusPanel priceBorder = new RadiusPanel(45, 3, new Color(200, 200, 200, 75));
        priceBorder.setPreferredSize(new Dimension(400, 50));
        priceBorder.setLayout(new FlowLayout(3, 25, 5));
        pricePanel.add(priceBorder);

        price = new JTextField(itemUpdate.getPrice() / 1000 + "");
        price.setPreferredSize(new Dimension(250, 40));
        price.setBorder(null);
        price.setOpaque(false);
        price.setFont(fieldFont);
        price.setForeground(Color.white);
        priceBorder.add(price);

        priceBorder.add(new JPanel() {
            {
                setPreferredSize(new Dimension(3, 35));
                setBackground(new Color(200, 200, 200, 150));
            }
        });

        thousand = new JTextField(String.format("%03d", itemUpdate.getPrice() % 1000));
        thousand.setPreferredSize(new Dimension(50, 40));
        thousand.setBorder(null);
        thousand.setOpaque(false);
        thousand.setFont(fieldFont);
        thousand.setForeground(Color.white);
        priceBorder.add(thousand);

        noCharTypeAndLength3(thousand);

        pricePanel.add(new JLabel() {
            {
                setPreferredSize(new Dimension(20, 50));
            }
        });

        JLabel vndLabel = new JLabel("vnđ");
        vndLabel.setPreferredSize(new Dimension(120, 50));
        vndLabel.setFont(new Font("Monospaced", Font.LAYOUT_LEFT_TO_RIGHT, 20));
        vndLabel.setForeground(Color.white);
        pricePanel.add(vndLabel);

        /* Temp */
        center.add(new JLabel());
        center.add(new JLabel());
    }

    private void south() {
        JPanel south = new JPanel(new FlowLayout(4, 40, 10));
        south.setPreferredSize(new Dimension(0, 80));
        south.setOpaque(false);
        add(south, "South");

        updateBorder = new RadiusPanel(45, 3, new Color(200, 200, 200, 0), new Color[]{
            new Color(6, 236, 254, 175),
            new Color(6, 254, 151, 175),
            new Color(6, 254, 6, 175),});
        updateBorder.setPreferredSize(new Dimension(170, 60));
        updateBorder.setLayout(new FlowLayout(3, 20, 10));
        south.add(updateBorder);

        update = new JLabel(" Update", ImageSupport.getSizedIcon(updateIcon, 30, 30), 0);
        update.setPreferredSize(new Dimension(130, 40));
        update.setFont(labelFont);
        update.setForeground(Color.white);
        updateBorder.add(update);
    }

    public Integer[] toIntegerArray(int length) {
        return IntStream.rangeClosed(1, length).boxed().toArray(Integer[]::new);
    }

    public Integer[] toIntegerArray(int start, int end) {
        return IntStream.rangeClosed(start, end).boxed().toArray(Integer[]::new);
    }

    private void noCharTypeAndLength3(JTextField textField) {
        AbstractDocument document = (AbstractDocument) textField.getDocument();
        DocumentFilter filter = new DocumentFilter() {
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                // Kiểm tra xem văn bản mới có phải là số không
                if (isNumber(text) && (fb.getDocument().getLength() + text.length() - length) <= 3) {
                    // Nếu là số, thực hiện thay thế
                    super.replace(fb, offset, length, text, attrs);
                } else {
                    // Nếu không phải số, không thực hiện thay thế
                    // Có thể hiển thị thông báo hoặc thực hiện hành động khác ở đây
                }
            }

            private boolean isNumber(String text) {
                // Kiểm tra xem văn bản có phải là số không
                try {
                    Double.valueOf(text);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        };

        document.setDocumentFilter(filter);
    }

    // Getter
    public JComboBox<Integer> getDayChooser() {
        return dayChooser;
    }

    public JComboBox<Integer> getYearChooser() {
        return yearChooser;
    }

    public JComboBox<String> getMonthChooser() {
        return monthChooser;
    }

    public Window getWindow() {
        return window;
    }

    public JLabel getErrorLabel() {
        return errorLabel;
    }

    public JTextField getItem() {
        return item;
    }

    public JTextField getCount() {
        return count;
    }

    public JTextField getPrice() {
        return price;
    }

    public JTextField getThousand() {
        return thousand;
    }

    public JCheckBox getNumber() {
        return number;
    }

    public JCheckBox getKg() {
        return kg;
    }

    public RadiusPanel getUpdateBorder() {
        return updateBorder;
    }

    public JLabel getUpdate() {
        return update;
    }

    public Item getItemUpdate() {
        return itemUpdate;
    }
}
