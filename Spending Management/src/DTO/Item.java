package DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 * @author Jhin
 */
public class Item {

    private String id;
    private LocalDateTime day;
    private String item;
    private int price;

    public Item() {
        this.id = "IT000";
        this.day = LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth(), LocalTime.now().getHour(), LocalTime.now().getMinute(), LocalTime.now().getSecond());
        this.item = "New Item";
        this.price = 0;
    }

    public Item(String id, LocalDateTime day, String item, int price) {
        this.id = id;
        this.day = day;
        this.item = item;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getDay() {
        return day;
    }

    public String getItem() {
        return item;
    }

    public int getPrice() {
        return price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDay(LocalDateTime day) {
        this.day = day;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item: " + id + ", " + day + ", " + item + ", " + price;
    }

}
