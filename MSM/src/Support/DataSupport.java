package Support;

import DTO.Item;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author Jhin
 */
public class DataSupport {

    public static String[][] toObjectData(ArrayList<Item> list) {
        String[][] data = new String[list.size()][];

        int index = 0;
        for (Item i : list) {
            if (i.isIsItem()) {
                data[index++] = new String[]{i.getId(), i.getItem(), toDataTime(i.getDateTime()), i.getCount() + "item", i.getPrice() + "vnđ"};
            } else {
                data[index++] = new String[]{i.getId(), i.getItem(), toDataTime(i.getDateTime()), i.getCount() + "kg", i.getPrice() + "vnđ"};
            }
        }

        return data;
    }

    public static String toDataTime(LocalDateTime ldt) {
        return String.format("%02d:%02d:%02d %d-%d-%d", ldt.getHour(), ldt.getMinute(), ldt.getSecond(), ldt.getDayOfMonth(), ldt.getMonthValue(), ldt.getYear());
    }
}
