package bo.custom.impl;

import bo.custom.PurchasePlaceProgramBO;
import dao.DAOFactory;
import dao.custom.ProgramDAO;
import dao.custom.StudentDAO;
import dao.custom.StudentProgramDAO;
import dto.ProgramDTO;
import dto.StudentDTO;
import dto.StudentProgramDTO;
import entity.Program;
import entity.Student;
import entity.StudentProgram;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchasePlaceProgramBOImpl implements PurchasePlaceProgramBO {
    StudentProgramDAO studentProgramDAO = (StudentProgramDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT_PROGRAM);
    StudentDAO studentDAO =(StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    ProgramDAO programDAO =(ProgramDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PROGRAM);

    @Override
    public List<StudentDTO> getAllStudents() {

        try {
            List<Student> all  = studentDAO.getAll();
            List<StudentDTO> studentDTOList=new ArrayList<>();
            for (Student student : all) {
                studentDTOList.add(new StudentDTO(student.getId(),student.getTitle(),student.getName(),student.getAddress(),student.getCity(),student.getFee(),student.getAge()));
            }
            return studentDTOList;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
           return null;

    }

    @Override
    public List<ProgramDTO> getAllPrograms()  {

        try {
            List<Program> all  = programDAO.getAll();
            List<ProgramDTO> programDTOS=new ArrayList<>();
            for (Program program : all) {
                programDTOS.add(new ProgramDTO(program.getCode(),program.getDescription(),program.getDuration(),program.getpFee(),program.getFreeSpace()));
            }
            return programDTOS;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public ProgramDTO searchProgram(String code) throws SQLException, ClassNotFoundException {
        Program search = programDAO.search(code);
       return new ProgramDTO(search.getCode(),search.getDescription(),search.getDuration(),search.getpFee(),search.getFreeSpace());
    }

    @Override
    public void purchasePlaceProgram(StudentProgramDTO dto)  {
        StudentDTO studentDTO = dto.getStudentDTO();
        ProgramDTO programDTO =dto.getProgramDTO();
        Student student = new Student(studentDTO.getId(), studentDTO.getTitle(), studentDTO.getName(), studentDTO.getAddress(), studentDTO.getCity(), studentDTO.getFee(), studentDTO.getAge());
        Program program = new Program(programDTO.getCode(),programDTO.getDescription(),programDTO.getDuration(),programDTO.getpFee(),programDTO.getFreeSpace());

        try {

            studentProgramDAO.add(new StudentProgram(student,program));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean ifProgramExist(String code) throws SQLException, ClassNotFoundException {
        return programDAO.ifProgramExist(code);
    }

    @Override
    public boolean ifStudentExist(String id) throws SQLException, ClassNotFoundException {
        return studentDAO.ifStudentExist(id);
    }

    @Override
    public StudentDTO searchStudent(String s) throws SQLException, ClassNotFoundException {
        Student search = studentDAO.search(s);
       return new  StudentDTO(search.getId(),search.getTitle(),search.getName(),search.getAddress(),search.getCity(),search.getFee(),search.getAge());
    }
}
