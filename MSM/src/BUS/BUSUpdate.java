package BUS;

import DAO.DAOItem;
import DTO.Item;
import DataList.ListItem;
import GUI.Update;
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
public class BUSUpdate {

    private Update update;

    public BUSUpdate(Update update) {
        this.update = update;
        events();
    }

    private void events() {
        /* ---------- Chuyển tháng, set lại ngày ---------- */
        update.getMonthChooser().addItemListener((ItemEvent e) -> {
            // Kiểm tra sự kiện là ItemEvent.SELECTED (chỉ xử lý khi một mục được chọn)
            if (e.getStateChange() == ItemEvent.SELECTED) {
                int day = (int) update.getDayChooser().getSelectedItem();
                int month = Integer.parseInt(((String) update.getMonthChooser().getSelectedItem()).substring(6));
//                int month = Integer.parseInt(((String) update.getMonthChooser().getSelectedItem()).split(" ")[1]);
                int year = (int) update.getYearChooser().getSelectedItem();

                update.getDayChooser().setModel(new DefaultComboBoxModel<>(toIntegerArray(LocalDate.of(year, getMonth(month), day).lengthOfMonth())));
            }
        });

        /* ---------- Chuyển năm set lại ngày tháng 2 nếu nhuận ---------- */
        update.getYearChooser().addItemListener((ItemEvent e) -> {
            // Kiểm tra sự kiện là ItemEvent.SELECTED (chỉ xử lý khi một mục được chọn)
            if (e.getStateChange() == ItemEvent.SELECTED) {
                int day = (int) update.getDayChooser().getSelectedItem();
                int month = Integer.parseInt(((String) update.getMonthChooser().getSelectedItem()).substring(6));
                int year = (int) update.getYearChooser().getSelectedItem();

                update.getDayChooser().setModel(new DefaultComboBoxModel<>(toIntegerArray(LocalDate.of(year, getMonth(month), day).lengthOfMonth())));
            }
        });

        update.getUpdate().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("[BUSUpdate]: Update");
                int day = (int) update.getDayChooser().getSelectedItem();
                int month = Integer.parseInt(((String) update.getMonthChooser().getSelectedItem()).substring(6));
                int year = (int) update.getYearChooser().getSelectedItem();
                String item = update.getItem().getText();
                String count = update.getCount().getText();
                boolean number = update.getNumber().isSelected();
                boolean kg = update.getKg().isSelected();
                String price = update.getPrice().getText();
                String thousand = update.getThousand().getText();
                LocalDate dateUpdate = LocalDate.of(year, month, day);
                LocalTime timeUpdate = LocalTime.now();
                LocalDateTime ldt = LocalDateTime.of(dateUpdate, timeUpdate);

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
                    return;
                }

                /* ---------- Add List, Database ---------- */
                Item updateItem = new Item(update.getItemUpdate().getId(), item, ldt, Float.parseFloat(count), number, Integer.parseInt(price + thousand));
//                System.out.println("Update: " + updateItem);
                ListItem.update(updateItem);
                update.getWindow().getHome().getTable().setData(DataSupport.toObjectData(ListItem.getListItem()));
                update.getWindow().getCardLayout().show(update.getWindow().getCenterOfCenter(), "Home");
                update.getWindow().newFilter();
                DAOItem.GetInstance().Update(updateItem);

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
                update.getUpdateBorder().setBackgroundColor(new Color(200, 200, 200, 75));
                update.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                update.getUpdateBorder().setBackgroundColor(new Color(200, 200, 200, 0));
                update.repaint();
            }
        });
    }

    // Method
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
