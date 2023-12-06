package DataList;

import DAO.DAOItem;
import DTO.Item;
import java.util.ArrayList;

/**
 *
 * @author Jhin
 */
@SuppressWarnings("StaticNonFinalUsedInInitialization")
public class ListItem {

    /* Purpose for render */
    private static ArrayList<Item> listItem = DAOItem.GetInstance().SelectAll();
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

    public static String newId() {
        String id;
        int index = 1;

        for (Item i : listItem) {
            if (!(Integer.parseInt(i.getId().substring(2)) == index)) {
                id = "IT" + String.format("%03d", index);
                return id;
            }
            index++;
        }
        id = "IT" + String.format("%03d", index);

        return id;
    }

    public static void renewListSortFilter() {
        listSortFilter.clear();
        listSortFilter = new ArrayList<>(listItem);
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
