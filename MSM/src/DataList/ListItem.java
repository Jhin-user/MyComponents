package DataList;

import DAO.DAOItem;
import DTO.Item;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Jhin
 */
@SuppressWarnings("StaticNonFinalUsedInInitialization")
public class ListItem {

    /* Purpose for render */
    private static ArrayList<Item> listItem = DAOItem.GetInstance().SelectByMonthYear(LocalDate.now().getMonthValue(), LocalDate.now().getYear());
    private static ArrayList<Item> listSortFilter = new ArrayList<>(listItem);

    public static void add(Item item) {
        listItem.add(item);
    }

    public static void update(Item item) {
        for (Item i : listItem) {
            if (item.getId().equals(i.getId())) {
                listItem.set(listItem.indexOf(i), item);
                break;
            }
        }
    }

    public static void delete(String id) {
        for (Item i : listItem) {
            if (id.equals(i.getId())) {
                listItem.remove(i);
                return;
            }
        }
    }

    public static void renewListMonthYear(int month, int year) {
        listItem = DAOItem.GetInstance().SelectByMonthYear(month, year);
        listSortFilter = new ArrayList<>(listItem);
    }

    public static void renewListDayMonthYear(int day, int month, int year) {
        listItem = DAOItem.GetInstance().SelectByDayMonthYear(day, month, year);
        listSortFilter = new ArrayList<>(listItem);
    }

    public static void renewListSortFilter() {
        listSortFilter.clear();
        listSortFilter = new ArrayList<>(listItem);
    }

    public static String newId(int day, int month, int year) {
        ArrayList<Item> listMonthOfYear = DAOItem.GetInstance().SelectByDayMonthYear(day, month, year);

        int index = 1;
        for (Item i : listMonthOfYear) {
            if (Integer.parseInt(i.getId().substring(4)) != index) {
                return String.format("%d%02d%03d", year % 100, month, index);
            }
            index++;
        }
        return String.format("%d%02d%03d", year % 100, month, index);
    }

    // Getter
    public static ArrayList<Item> getListItem() {
        return listItem;
    }

    public static ArrayList<Item> getListSortFilter() {
        return listSortFilter;
    }

    // Setter
    public static void setListSortFilter(ArrayList<Item> listSortFilter) {
        ListItem.listSortFilter = listSortFilter;
    }

}
