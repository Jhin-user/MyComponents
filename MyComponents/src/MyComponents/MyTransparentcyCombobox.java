package MyComponents;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.plaf.basic.BasicComboBoxUI;

/**
 *
 * @author Jhin
 */
public class MyTransparentcyCombobox extends JComboBox<Object> {

    private ListCellRenderer renderer;

    public MyTransparentcyCombobox(Object[] items) {
        super(items);
        renderer = new BasicComboBoxRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component rendererComponent = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    setOpaque(false);
                if (isSelected) {
                    // Tô màu nền khi được chọn
                    rendererComponent.setBackground(new Color(100, 100, 255, 100)); // Màu nền trong suốt
                }
                return rendererComponent;
            }
        };
        setOpaque(false);
        setRenderer(renderer);
    }

}
