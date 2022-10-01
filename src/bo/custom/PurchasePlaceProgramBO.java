package bo.custom;

import bo.SuperBO;
import dto.StudentDTO;
import dto.ProgramDTO;
import dto.StudentProgramDTO;

import java.sql.SQLException;
import java.util.List;

public interface PurchasePlaceProgramBO extends SuperBO {
    List<StudentDTO> getAllStudents();
    List<ProgramDTO> getAllPrograms();
    ProgramDTO searchProgram(String code)throws SQLException, ClassNotFoundException;
    void purchasePlaceProgram(StudentProgramDTO dto) ;
    boolean ifProgramExist(String code) throws SQLException, ClassNotFoundException;
    boolean ifStudentExist(String id) throws SQLException, ClassNotFoundException;
    StudentDTO searchStudent(String s)throws SQLException, ClassNotFoundException;
}
