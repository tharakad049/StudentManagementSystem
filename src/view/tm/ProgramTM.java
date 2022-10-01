package view.tm;

import java.math.BigDecimal;
public class ProgramTM {
     private String code;
     private String description;
     private String duration;
     private BigDecimal pFee;
     private int freeSpace;

    public ProgramTM() {
    }

    public ProgramTM(String code, String description, String duration, BigDecimal pFee, int freeSpace) {
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
        return "ProgramTM{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", duration='" + duration + '\'' +
                ", pFee=" + pFee +
                ", freeSpace=" + freeSpace +
                '}';
    }
}
