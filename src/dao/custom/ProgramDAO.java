package dao.custom;

import dao.CrudDAO;
import entity.Program;

import java.sql.SQLException;

public interface ProgramDAO extends CrudDAO<Program, String> {
    boolean ifProgramExist(String code) throws SQLException, ClassNotFoundException;
    String generateNewID() throws SQLException, ClassNotFoundException;
}
