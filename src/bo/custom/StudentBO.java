package bo.custom;

import bo.SuperBO;
import dto.StudentDTO;
import entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface StudentBO extends SuperBO {
    List<StudentDTO> getAllStudents() throws SQLException, ClassNotFoundException;
    void addStudent(StudentDTO studentDTO) throws SQLException, ClassNotFoundException;
    void updateStudent(StudentDTO studentDTO) throws SQLException, ClassNotFoundException;
    void deleteStudent(StudentDTO studentDTO) throws SQLException, ClassNotFoundException;
    Student searchStudent(String id) throws SQLException, ClassNotFoundException;
    boolean ifStudentExist(String id) throws SQLException, ClassNotFoundException;
    int countStudent() throws SQLException, ClassNotFoundException;
    String generateNewID() throws SQLException, ClassNotFoundException;
}
