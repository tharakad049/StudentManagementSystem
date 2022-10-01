package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CrudDAO<S, ID> extends SuperDAO {
    void add(S t) throws SQLException, ClassNotFoundException;
    void update(S s) throws SQLException, ClassNotFoundException;
    void delete(S s) throws SQLException, ClassNotFoundException;
    S search(ID id) throws SQLException, ClassNotFoundException;
    List<S> getAll() throws SQLException, ClassNotFoundException;
}
