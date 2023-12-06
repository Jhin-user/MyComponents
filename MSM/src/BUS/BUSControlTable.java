package BUS;

import DTO.Item;
import DataList.ListItem;
import GUI.Window;
import Support.DataSupport;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author Jhin
 */
public class BUSControlTable {

    private Window window;

    public BUSControlTable(Window window) {
        this.window = window;
        events();
    }

    private void events() {
        if (window.getBackLabel() != null) {
            window.getBackLabel().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("[BUSControlTable]: Back");
                    window.getCardLayout().show(window.getCenterOfCenter(), "Home");
                    window.newFilter();
                    window.getHome().getTable().clearSelectedRow();
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    window.getBackLabel().setBackground(new Color(255, 255, 255, 100));
                    window.getBackLabel().setOpaque(true);
                    window.getAddressPanel().repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    window.getBackLabel().setBackground(new Color(255, 255, 255, 0));
                    window.getBackLabel().setOpaque(false);
                    window.getAddressPanel().repaint();
                }
            });
        }

        window.getSortCbb().addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedItem = (String) e.getItem();

                sortBy(selectedItem);
            }
        });

        window.getFilterCbb().addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String sortSelected = (String) window.getSortCbb().getSelectedItem();
                String selectedItem = (String) e.getItem();
                
                if (sortSelected != null) {
                    sortBy(sortSelected);
                }

                switch (selectedItem) {
                    case "Items" -> {
                        ListItem.renewListSortFilter();
                        ListItem.setListSortFilter(filterItem(ListItem.getListSortFilter(), 0));
                        window.getHome().getTable().setData(DataSupport.toObjectData(ListItem.getListSortFilter()));
                    }
                    case "Kg" -> {
                        ListItem.renewListSortFilter();
                        ListItem.setListSortFilter(filterItem(ListItem.getListSortFilter(), 1));
                        window.getHome().getTable().setData(DataSupport.toObjectData(ListItem.getListSortFilter()));
                    }
                }
            }
        });

        window.getClearSortFilter().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("[BUSControlTable]: Clear sort filter");
                window.getSortCbb().setSelectedIndex(-1);
                window.getFilterCbb().setSelectedIndex(-1);
                window.getHome().getTable().setData(DataSupport.toObjectData(ListItem.getListItem()));
                window.getHome().getTable().clearSelectedRow();
                ListItem.renewListSortFilter();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                window.getClearSortFilter().setBackground(new Color(255, 255, 255, 100));
                window.getClearSortFilter().setOpaque(true);
                window.getFilterPanel().repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                window.getClearSortFilter().setBackground(new Color(255, 255, 255, 0));
                window.getClearSortFilter().setOpaque(false);
                window.getFilterPanel().repaint();
            }
        });
    }

    // Method
    private void sortBy(String selectedItem) {
        switch (selectedItem) {
            case "ID(a)" -> {
                window.getHome().getTable().setData(DataSupport.toObjectData(sortItemBy(ListItem.getListSortFilter(), 0)));
            }
            case "ID(d)" -> {
                window.getHome().getTable().setData(DataSupport.toObjectData(sortItemBy(ListItem.getListSortFilter(), 1)));
            }
            case "Day(a)" -> {
                window.getHome().getTable().setData(DataSupport.toObjectData(sortItemBy(ListItem.getListSortFilter(), 2)));
            }
            case "Day(d)" -> {
                window.getHome().getTable().setData(DataSupport.toObjectData(sortItemBy(ListItem.getListSortFilter(), 3)));
            }
        }
    }

    private ArrayList<Item> sortItemBy(ArrayList<Item> listItem, int sortType) {
        switch (sortType) {
            case 0 -> {
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
            case 1 -> {
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
            case 2 -> {
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
            case 3 -> {
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

    private ArrayList<Item> filterItem(ArrayList<Item> listItem, int filterType) {
        ArrayList<Item> filterList = new ArrayList<>();

        switch (filterType) {
            case 0 -> {
                for (Item i : listItem) {
                    if (i.isIsItem()) {
                        filterList.add(i);
                    }
                }
            }
            case 1 -> {
                for (Item i : listItem) {
                    if (!i.isIsItem()) {
                        filterList.add(i);
                    }
                }
            }
        }

        return filterList;
    }
}
