package view.tm;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PlaceProgramTM {
    private String placeProgramId;
    private LocalDate placeProgramDate;
    private String studentId;
    private String studentName;
    private BigDecimal placeProgramTotal;

    public PlaceProgramTM() {
    }

    public PlaceProgramTM(String placeProgramId, LocalDate placeProgramDate, String studentId, String studentName, BigDecimal placeProgramTotal) {
        this.setPlaceProgramId(placeProgramId);
        this.setPlaceProgramDate(placeProgramDate);
        this.setStudentId(studentId);
        this.setStudentName(studentName);
        this.setPlaceProgramTotal(placeProgramTotal);
    }

    public String getPlaceProgramId() {
        return placeProgramId;
    }

    public void setPlaceProgramId(String placeProgramId) {
        this.placeProgramId = placeProgramId;
    }

    public LocalDate getPlaceProgramDate() {
        return placeProgramDate;
    }

    public void setPlaceProgramDate(LocalDate placeProgramDate) {
        this.placeProgramDate = placeProgramDate;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public BigDecimal getPlaceProgramTotal() {
        return placeProgramTotal;
    }

    public void setPlaceProgramTotal(BigDecimal placeProgramTotal) {
        this.placeProgramTotal = placeProgramTotal;
    }

    @Override
    public String toString() {
        return "PlaceProgramTM{" +
                "placeProgramId='" + placeProgramId + '\'' +
                ", placeProgramDate=" + placeProgramDate +
                ", studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", placeProgramTotal=" + placeProgramTotal +
                '}';
    }
}

