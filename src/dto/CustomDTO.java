package dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CustomDTO{

    private String placeProgramId;
    private LocalDate placeProgramDate;
    private String studentId;
    private String programCode;
    private int qty;
    private BigDecimal programFee;

    public CustomDTO() {
    }

    public CustomDTO(String placeProgramId, LocalDate placeProgramDate, String studentId, String programCode, int qty, BigDecimal programFee) {
        this.setPlaceProgramId(placeProgramId);
        this.setPlaceProgramDate(placeProgramDate);
        this.setStudentId(studentId);
        this.setProgramCode(programCode);
        this.setQty(qty);
        this.setProgramFee(programFee);
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

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public BigDecimal getProgramFee() {
        return programFee;
    }

    public void setProgramFee(BigDecimal programFee) {
        this.programFee = programFee;
    }

    @Override
    public String toString() {
        return "CustomDTO{" +
                "placeProgramId='" + placeProgramId + '\'' +
                ", placeProgramDate=" + placeProgramDate +
                ", studentId='" + studentId + '\'' +
                ", programCode='" + programCode + '\'' +
                ", qty=" + qty +
                ", programFee=" + programFee +
                '}';
    }
}
