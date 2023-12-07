package BUS;

import DataList.ListItem;
import GUI.Window;
import Support.DataSupport;
import Support.FilterType;
import Support.SortType;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
                        ListItem.setListSortFilter(DataSupport.filterItem(ListItem.getListSortFilter(), FilterType.ITEM));
                        window.getHome().getTable().setData(DataSupport.toObjectData(ListItem.getListSortFilter()));
                    }
                    case "Kg" -> {
                        ListItem.renewListSortFilter();
                        ListItem.setListSortFilter(DataSupport.filterItem(ListItem.getListSortFilter(), FilterType.KG));
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

        window.getMonthCbb().addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                int month = Integer.parseInt(window.getMonthCbb().getSelectedItem().toString().split(" ")[1]);
                int year = (int) window.getYearCbb().getSelectedItem();

                ListItem.renewListByMonthofYear(month, year);
                window.getHome().getTable().setData(DataSupport.toObjectData(ListItem.getListItem()));
            }
        });
        
        window.getYearCbb().addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                int month = Integer.parseInt(window.getMonthCbb().getSelectedItem().toString().split(" ")[1]);
                int year = (int) window.getYearCbb().getSelectedItem();

                ListItem.renewListByMonthofYear(month, year);
                window.getHome().getTable().setData(DataSupport.toObjectData(ListItem.getListItem()));
            }
        });
    }

    // Method
    private void sortBy(String selectedItem) {
        switch (selectedItem) {
            case "ID(a)" -> {
                window.getHome().getTable().setData(DataSupport.toObjectData(DataSupport.sortItemById(ListItem.getListSortFilter(), SortType.IDASCENDED)));
            }
            case "ID(d)" -> {
                window.getHome().getTable().setData(DataSupport.toObjectData(DataSupport.sortItemById(ListItem.getListSortFilter(), SortType.IDDESCENDED)));
            }
            case "Day(a)" -> {
                window.getHome().getTable().setData(DataSupport.toObjectData(DataSupport.sortItemById(ListItem.getListSortFilter(), SortType.DAYASCENDED)));
            }
            case "Day(d)" -> {
                window.getHome().getTable().setData(DataSupport.toObjectData(DataSupport.sortItemById(ListItem.getListSortFilter(), SortType.DAYDESCENDED)));
            }
        }
    }
}
