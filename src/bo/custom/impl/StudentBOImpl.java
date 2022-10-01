package bo.custom.impl;

import bo.custom.StudentBO;
import dao.DAOFactory;
import dao.SuperDAO;
import dao.custom.StudentDAO;
import dto.StudentDTO;
import entity.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
    public static char[] countCustomers;
    ResultSet rst;
    StudentDAO studentDAO =(StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    @Override
    public boolean ifStudentExist(String id) throws SQLException, ClassNotFoundException {
        return studentDAO.ifStudentExist(id);
    }

    @Override
    public int countStudent() throws SQLException, ClassNotFoundException {
        String s = studentDAO.countStudent();
        return Integer.valueOf(s);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return studentDAO.generateNewID();
    }


    @Override
    public void deleteStudent(StudentDTO studentDTO) throws SQLException, ClassNotFoundException {

        Student student = new Student(studentDTO.getId(), studentDTO.getTitle(), studentDTO.getName(), studentDTO.getAddress(), studentDTO.getCity(), studentDTO.getFee(), studentDTO.getAge());
        studentDAO.delete(student);

    }

    @Override
    public Student searchStudent(String id) throws SQLException, ClassNotFoundException {
        return studentDAO.search(id);
    }

    @Override
    public List<StudentDTO> getAllStudents() throws SQLException, ClassNotFoundException {
        List<Student> all = studentDAO.getAll();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student : all) {
            studentDTOS.add(new StudentDTO(student.getId(),student.getTitle(),student.getName(),student.getAddress(),student.getCity(),student.getFee(),student.getAge()));
        }
        return studentDTOS;
    }

    @Override
    public void addStudent(StudentDTO dto) throws SQLException, ClassNotFoundException {
         studentDAO.add(new Student(dto.getId(),dto.getTitle(),dto.getName(),dto.getAddress(),dto.getCity(),dto.getFee(),dto.getAge()));

    }

    @Override
    public void updateStudent(StudentDTO dto) throws SQLException, ClassNotFoundException {
          studentDAO.update(new Student(dto.getId(),dto.getTitle(),dto.getName(),dto.getAddress(),dto.getCity(),dto.getFee(),dto.getAge()));
    }
}
