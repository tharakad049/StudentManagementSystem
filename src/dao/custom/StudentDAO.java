package dao.custom;

import dao.CrudDAO;
import entity.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface StudentDAO extends CrudDAO<Student, String> {

    boolean ifStudentExist(String id) throws SQLException, ClassNotFoundException;
    String generateNewID() throws SQLException, ClassNotFoundException;
    String countStudent() throws SQLException, ClassNotFoundException;
}
