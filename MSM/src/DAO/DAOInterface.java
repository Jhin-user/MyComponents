package DAO;

import java.util.ArrayList;

/**
 *
 * @author Jhin
 * @param <T>
 */
public interface DAOInterface<T> {

    public ArrayList<T> SelectAll();

    public boolean Insert(T item);

    public boolean Update(T item);

    public boolean Delete(String itemId);
}
