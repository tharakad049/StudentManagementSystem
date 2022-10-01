package entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class StudentProgram implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "student_id")
    private
    Student student;

    @Id
    @ManyToOne
    @JoinColumn(name = "program_id")
    private
    Program program;
    @CreationTimestamp
    private
    Date registerDate;

    public StudentProgram() {
    }

    public StudentProgram(Student student, Program program) {
        this.setStudent(student);
        this.setProgram(program);
    }




    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
}
