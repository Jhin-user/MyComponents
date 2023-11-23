package DAO;

import Connection.ConnectionDatabase;
import DTO.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Jhin
 */
public class DAOItem implements DAOInterface<Item> {

    public static DAOItem getInstace() {
        return new DAOItem();
    }

    @Override
    @SuppressWarnings("ConvertToTryWithResources")
    public ArrayList<Item> SelectAll() {
        ArrayList<Item> listItem = new ArrayList<>();
        String query = "select * from Item";

        try {
            Connection connection = ConnectionDatabase.GetConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                listItem.add(new Item(rs.getString("id"), rs.getTimestamp("day").toLocalDateTime(), rs.getString("item"), rs.getInt("price")));
            }
            connection.close();

            return listItem;
        } catch (SQLException e) {
        }

        return null;
    }

    @Override
    public int Add(Item item) {
        String queryUpdate = "insert into Item values(?, ?, ?, ?)";
        int rowAffect = 0;

        try {
            Connection connection = ConnectionDatabase.GetConnection();
            PreparedStatement ps = connection.prepareStatement(queryUpdate);

            ps.setString(1, item.getId());
            ps.setTimestamp(2, Timestamp.valueOf(item.getDay()));
            ps.setString(3, item.getItem());
            ps.setInt(4, item.getPrice());
            rowAffect = ps.executeUpdate();

            System.out.println("[DaoItem/Add]: " + queryUpdate + "\nRow affect: " + rowAffect);
        } catch (SQLException e) {
        }

        return rowAffect;

    }

    @Override
    public int Update(Item item) {
        String queryUpdate = "update Item set day = ?, item = ?, price = ? where id = ?";
        int rowAffect = 0;

        try {
            Connection connection = ConnectionDatabase.GetConnection();
            PreparedStatement ps = connection.prepareStatement(queryUpdate);

            ps.setTimestamp(1, Timestamp.valueOf(item.getDay()));
            ps.setString(2, item.getItem());
            ps.setInt(3, item.getPrice());
            ps.setString(4, item.getId());
            rowAffect = ps.executeUpdate();

            System.out.println("[DaoItem/Update]: " + queryUpdate + "\nRow affect: " + rowAffect);
        } catch (SQLException e) {
        }

        return rowAffect;
    }

    @Override
    public int Delete(Item item) {
        String queryUpdate = "delete Item where id = ?";
        int rowAffect = 0;

        try {
            Connection connection = ConnectionDatabase.GetConnection();
            PreparedStatement ps = connection.prepareStatement(queryUpdate);

            ps.setString(1, item.getId());
            rowAffect = ps.executeUpdate();

            System.out.println("[DaoItem/Delete]: " + queryUpdate + "\nRow affect: " + rowAffect);
        } catch (SQLException e) {
        }

        return rowAffect;
    }
}
