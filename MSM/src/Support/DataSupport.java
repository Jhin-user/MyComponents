package Support;

import DTO.Item;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 *
 * @author Jhin
 */
public class DataSupport {

    public static String[][] toObjectData(ArrayList<Item> list) {
        String[][] data = new String[list.size()][];

        int index = 0;
        for (Item i : list) {
            if (i.getCount() % (int) i.getCount() == 0) {
                data[index++] = new String[]{i.getId(), i.getItem(), toDataTime(i.getDateTime()), (int) i.getCount() + (i.isIsItem() ? " item" : " kg"), i.getPrice() + " vnđ"};
            } else {
                data[index++] = new String[]{i.getId(), i.getItem(), toDataTime(i.getDateTime()), i.getCount() + (i.isIsItem() ? " item" : " kg"), i.getPrice() + " vnđ"};
            }
        }

        return data;
    }

    public static Integer[] toIntegerArray(int length) {
        return IntStream.rangeClosed(1, length).boxed().toArray(Integer[]::new);
    }

    public static Integer[] toIntegerArray(int start, int end) {
        return IntStream.rangeClosed(start, end).boxed().toArray(Integer[]::new);
    }

    public static ArrayList<Item> sortItemById(ArrayList<Item> listItem, SortType type) {
        switch (type) {
            case IDASCENDED -> {
                /* Sort ID Ascended */
                for (int i = 0; i < listItem.size() - 1; i++) {
                    for (int j = i + 1; j < listItem.size(); j++) {
                        if (Integer.parseInt(listItem.get(i).getId().substring(2)) > Integer.parseInt(listItem.get(j).getId().substring(2))) {
                            Item temp = listItem.get(i);
                            listItem.set(i, listItem.get(j));
                            listItem.set(j, temp);
                        }
                    }
                }
            }
            case IDDESCENDED -> {
                /* Sort Id Descended */
                for (int i = 0; i < listItem.size() - 1; i++) {
                    for (int j = i + 1; j < listItem.size(); j++) {
                        if (Integer.parseInt(listItem.get(i).getId().substring(2)) < Integer.parseInt(listItem.get(j).getId().substring(2))) {
                            Item temp = listItem.get(i);
                            listItem.set(i, listItem.get(j));
                            listItem.set(j, temp);
                        }
                    }
                }
            }
            case DAYASCENDED -> {
                /* Sort day Ascended */
                for (int i = 0; i < listItem.size() - 1; i++) {
                    for (int j = i + 1; j < listItem.size(); j++) {
                        if (listItem.get(i).getDateTime().getDayOfMonth() > listItem.get(j).getDateTime().getDayOfMonth()) {
                            Item temp = listItem.get(i);
                            listItem.set(i, listItem.get(j));
                            listItem.set(j, temp);
                        }
                    }
                }
            }
            case DAYDESCENDED -> {
                /* Sort day Descended */
                for (int i = 0; i < listItem.size() - 1; i++) {
                    for (int j = i + 1; j < listItem.size(); j++) {
                        if (listItem.get(i).getDateTime().getDayOfMonth() < listItem.get(j).getDateTime().getDayOfMonth()) {
                            Item temp = listItem.get(i);
                            listItem.set(i, listItem.get(j));
                            listItem.set(j, temp);
                        }
                    }
                }
            }
        }

        return listItem;
    }

    public static ArrayList<Item> filterItem(ArrayList<Item> listItem, FilterType type) {
        ArrayList<Item> filterList = new ArrayList<>();

        switch (type) {
            case ITEM -> {
                for (Item i : listItem) {
                    if (i.isIsItem()) {
                        filterList.add(i);
                    }
                }
            }
            case KG -> {
                for (Item i : listItem) {
                    if (!i.isIsItem()) {
                        filterList.add(i);
                    }
                }
            }
        }

        return filterList;
    }

    public static String toDataTime(LocalDateTime ldt) {
        return String.format("%02d:%02d:%02d %d-%d-%d", ldt.getHour(), ldt.getMinute(), ldt.getSecond(), ldt.getDayOfMonth(), ldt.getMonthValue(), ldt.getYear());
    }

    public static Month getMonth(int month) {
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
