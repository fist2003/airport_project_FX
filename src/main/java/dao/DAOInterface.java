package dao;

import java.util.ArrayList;

/**
 * Created by ПК on 08.12.2016.
 */
public interface DAOInterface<T> {
    boolean insertNewDAO(T t);
    boolean editInDBDAO(T t);
    boolean deleteInDBDAO(T t);
    ArrayList<T> getAllDAO();
    T getByIdDAO(T t);
}
