package DAO;

import ConnectionDatabase.ConnectionDatabase;
import DTO.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author Jhin
 */
public class DAOItem implements DAOInterface<Item> {

    public static DAOItem GetInstance() {
        return new DAOItem();
    }

    @Override
    @SuppressWarnings({"ConvertToTryWithResources", "null"})
    public ArrayList<Item> SelectAll() {
        ArrayList<Item> listItem = new ArrayList<>();
        String query = "select * from ITEM";

        try {
            Connection connection = ConnectionDatabase.GetConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String id = rs.getString("ID");
                String item = rs.getString("item");
                LocalDateTime dateTime = rs.getTimestamp("dateTime").toLocalDateTime();
                float count = rs.getFloat("count");
                boolean isItem = rs.getBoolean("isItem");
                int price = rs.getInt("price");

                listItem.add(new Item(id, item, dateTime, count, isItem, price));
            }

            rs.close();
            ps.close();
            connection.close();
        } catch (SQLException e) {
        }

        return listItem;
    }

    @Override
    @SuppressWarnings("ConvertToTryWithResources")
    public boolean Insert(Item item) {
        String query = "insert into ITEM values(?, ?, ?, ?, ?, ?)";

        try {
            Connection connection = ConnectionDatabase.GetConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            
            ps.setString(1, item.getId());
            ps.setString(2, item.getItem());
            ps.setObject(3, Timestamp.valueOf(item.getDateTime()));
            ps.setFloat(4, item.getCount());
            ps.setBoolean(5, item.isIsItem());
            ps.setInt(6, item.getPrice());
            if (ps.executeUpdate() == 0) {
                return false;
            }

            ps.close();
            connection.close();
        } catch (SQLException e) {
        }
        
        return true;
    }

    @Override
    public boolean Update(Item item) {
        return true;
    }

    @Override
    public boolean Delete(Item item) {
        return true;
    }

}
