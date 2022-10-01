package dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class StudentProgramDTO {
    StudentDTO studentDTO;
    ProgramDTO programDTO;

    public StudentProgramDTO(StudentDTO studentDTO, ProgramDTO programDTO) {
        this.studentDTO = studentDTO;
        this.programDTO = programDTO;
    }

    public StudentProgramDTO() {
    }

    public StudentDTO getStudentDTO() {
        return studentDTO;
    }

    public void setStudentDTO(StudentDTO studentDTO) {
        this.studentDTO = studentDTO;
    }

    public ProgramDTO getProgramDTO() {
        return programDTO;
    }

    public void setProgramDTO(ProgramDTO programDTO) {
        this.programDTO = programDTO;
    }
}
