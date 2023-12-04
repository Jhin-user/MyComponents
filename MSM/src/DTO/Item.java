package DTO;

import java.time.LocalDateTime;

/**
 *
 * @author Jhin
 */
public class Item {

    private String id, item;
    private LocalDateTime dateTime;
    private float count;
    private boolean isItem;
    private int price;

    public Item(String id, String item, LocalDateTime dateTime, float count, boolean isItem, int price) {
        this.id = id;
        this.item = item;
        this.dateTime = dateTime;
        this.count = count;
        this.isItem = isItem;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("[%s, %s, %s, %f, %s, %d]", id, item, dateTime, count, String.valueOf(isItem), price);
    }

    public String getId() {
        return id;
    }

    public String getItem() {
        return item;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public float getCount() {
        return count;
    }

    public boolean isIsItem() {
        return isItem;
    }

    public int getPrice() {
        return price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setCount(float count) {
        this.count = count;
    }

    public void setIsItem(boolean isItem) {
        this.isItem = isItem;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
