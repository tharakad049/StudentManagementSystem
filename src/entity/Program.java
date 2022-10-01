package entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
@Entity
public class Program implements Serializable {
    @Id
    @Column(name = "program_id")
     private String code;
     private String description;
     private String duration;
     private BigDecimal pFee;
     private int freeSpace;

     @OneToMany(mappedBy = "program",cascade = CascadeType.ALL)
     Set<StudentProgram> studentPrograms=new HashSet<>();

    public Program() {
    }

    public Program(String code, String description, String duration, BigDecimal pFee, int freeSpace) {
        this.setCode(code);
        this.setDescription(description);
        this.setDuration(duration);
        this.setpFee(pFee);
        this.setFreeSpace(freeSpace);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public BigDecimal getpFee() {
        return pFee;
    }

    public void setpFee(BigDecimal pFee) {
        this.pFee = pFee;
    }

    public int getFreeSpace() {
        return freeSpace;
    }

    public void setFreeSpace(int freeSpace) {
        this.freeSpace = freeSpace;
    }

    @Override
    public String toString() {
        return "Program{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", duration='" + duration + '\'' +
                ", pFee=" + pFee +
                ", freeSpace=" + freeSpace +
                '}';
    }
}
