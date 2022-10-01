package bo.custom;

import bo.SuperBO;
import dto.ProgramDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ProgramBO extends SuperBO {
    List<ProgramDTO> getAllPrograms() throws SQLException, ClassNotFoundException;
    void addProgram(ProgramDTO dto) throws SQLException, ClassNotFoundException;
    void updateProgram(ProgramDTO dto) throws SQLException, ClassNotFoundException;
    void deleteProgram(ProgramDTO programDTO) throws SQLException, ClassNotFoundException;
    boolean ifProgramExist(String code) throws SQLException, ClassNotFoundException;
    String generateNewID() throws SQLException, ClassNotFoundException;
}
