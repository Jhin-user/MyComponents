package DAO;

import java.util.ArrayList;

/**
 *
 * @author Jhin
 * @param <T>
 */
public interface DAOInterface<T> {

    public ArrayList<T> SelectAll();
    
    public int Add(T item);
    
    public int Update(T item);
    
    public int Delete(T item);
}
