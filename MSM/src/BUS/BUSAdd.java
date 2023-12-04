package BUS;

import DAO.DAOItem;
import DTO.Item;
import DataList.ListItem;
import GUI.Add;
import Support.DataSupport;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.stream.IntStream;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Jhin
 */
public class BUSAdd {

    private Add add;

    public BUSAdd(Add add) {
        this.add = add;
        events();
    }

    private void events() {
        /* ---------- Chuyển tháng, set lại ngày ---------- */
        add.getMonthChooser().addItemListener((ItemEvent e) -> {
            // Kiểm tra sự kiện là ItemEvent.SELECTED (chỉ xử lý khi một mục được chọn)
            if (e.getStateChange() == ItemEvent.SELECTED) {
                int day = (int) add.getDayChooser().getSelectedItem();
                int month = Integer.parseInt(((String) add.getMonthChooser().getSelectedItem()).substring(6));
//                int month = Integer.parseInt(((String) add.getMonthChooser().getSelectedItem()).split(" ")[1]);
                int year = (int) add.getYearChooser().getSelectedItem();

                add.getDayChooser().setModel(new DefaultComboBoxModel<>(toIntegerArray(LocalDate.of(year, getMonth(month), day).lengthOfMonth())));
            }
        });

        /* ---------- Chuyển năm set lại ngày tháng 2 nếu nhuận ---------- */
        add.getYearChooser().addItemListener((ItemEvent e) -> {
            // Kiểm tra sự kiện là ItemEvent.SELECTED (chỉ xử lý khi một mục được chọn)
            if (e.getStateChange() == ItemEvent.SELECTED) {
                int day = (int) add.getDayChooser().getSelectedItem();
                int month = Integer.parseInt(((String) add.getMonthChooser().getSelectedItem()).substring(6));
                int year = (int) add.getYearChooser().getSelectedItem();

                add.getDayChooser().setModel(new DefaultComboBoxModel<>(toIntegerArray(LocalDate.of(year, getMonth(month), day).lengthOfMonth())));
            }
        });

        add.getAdd().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("[BUSAdd]: Add");
                int day = (int) add.getDayChooser().getSelectedItem();
                int month = Integer.parseInt(((String) add.getMonthChooser().getSelectedItem()).substring(6));
                int year = (int) add.getYearChooser().getSelectedItem();
                String item = add.getItem().getText();
                String count = add.getCount().getText();
                boolean number = add.getNumber().isSelected();
                boolean kg = add.getKg().isSelected();
                String price = add.getPrice().getText();
                String thousand = add.getThousand().getText();
                LocalDate dateAdd = LocalDate.of(year, month, day);
                LocalTime timeAdd = LocalTime.now();
                LocalDateTime ldt = LocalDateTime.of(dateAdd, timeAdd);

                if (item.replaceAll(" ", "").equals("")) {
                    System.out.println("Item không bỏ trống!");
                    return;
                }
                if (count.replaceAll(" ", "").equals("")) {
                    System.out.println("Count không bỏ trống!");
                    return;
                }
                if (!count.matches("\\d*\\.?\\d+")) {
                    System.out.println("Sai định dạng Count!");
                    return;
                }
                // Radio Chekcbox?
                if (!number && !kg) {
                    System.out.println("Chọn lượng item: item(s) hay kg!");
                    return;
                }
                if (number && kg) {
                    System.out.println("Chỉ chọn một lượng item!");
                    return;
                }
                if (!price.matches("\\d+")) {
                    System.out.println("Sai định dạng Price!");
                }

                /* ---------- Add List, Database ---------- */
                Item insertItem = new Item(ListItem.newId(), item, ldt, Float.parseFloat(count), number, Integer.parseInt(price + thousand));
//                System.out.println("New Item: " + insertItem);
                ListItem.add(insertItem);
                add.getWindow().getHome().getTable().setData(DataSupport.toObjectData(ListItem.getListItem()));
                DAOItem.GetInstance().Insert(insertItem);

                /* -------------------- Show Info -------------------- */
                boolean showInfo = false;
                if (showInfo) {
                    System.out.println("Ten Item: " + item);
                    System.out.print("SL: " + count + " ");
                    if (number) {
                        System.out.println("items");
                    }
                    if (kg) {
                        System.out.println("kg");
                    }
                    System.out.println("Gia: " + price + "," + thousand);
                    System.out.println("Thoi gian: " + ldt);
                }
                /* -------------------------------------------------- */
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                add.getAddBorder().setBackgroundColor(new Color(200, 200, 200, 75));
                add.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                add.getAddBorder().setBackgroundColor(new Color(200, 200, 200, 0));
                add.repaint();
            }
        });
    }

    public Integer[] toIntegerArray(int length) {
        return IntStream.rangeClosed(1, length).boxed().toArray(Integer[]::new);
    }

    public Integer[] toIntegerArray(int start, int end) {
        return IntStream.rangeClosed(start, end).boxed().toArray(Integer[]::new);
    }

    public Month getMonth(int month) {
        switch (month) {
            case 1 -> {
                return Month.JANUARY;
            }
            case 2 -> {
                return Month.FEBRUARY;
            }
            case 3 -> {
                return Month.MARCH;
            }
            case 4 -> {
                return Month.APRIL;
            }
            case 5 -> {
                return Month.MAY;
            }
            case 6 -> {
                return Month.JUNE;
            }
            case 7 -> {
                return Month.JULY;
            }
            case 8 -> {
                return Month.AUGUST;
            }
            case 9 -> {
                return Month.SEPTEMBER;
            }
            case 10 -> {
                return Month.OCTOBER;
            }
            case 11 -> {
                return Month.NOVEMBER;
            }
            case 12 -> {
                return Month.DECEMBER;
            }
            default -> {
                System.out.println("[BUSAdd]: Worng month");
            }
        }
        return null;
    }
}
