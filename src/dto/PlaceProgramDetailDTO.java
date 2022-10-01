package dto;

import java.math.BigDecimal;

public class PlaceProgramDetailDTO {
    private String pid;
    private String programCode;
    private int qty;
    private BigDecimal programFee;

    public PlaceProgramDetailDTO() {
    }

    public PlaceProgramDetailDTO(String pid, String programCode, int qty, BigDecimal programFee) {
        this.setPid(pid);
        this.setProgramCode(programCode);
        this.setQty(qty);
        this.setProgramFee(programFee);
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
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
        return "PlaceProgramDetailDTO{" +
                "pid='" + pid + '\'' +
                ", programCode='" + programCode + '\'' +
                ", qty=" + qty +
                ", programFee=" + programFee +
                '}';
    }
/*    public PlaceProgramDetailDTO(String oid, String programCode, int qty, BigDecimal programFee) {
        this.setOid(oid);
        this.setProgramCode(programCode);
        this.setQty(qty);
        this.setProgramFee(programFee);
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
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
        return "OrderDetailDTO{" +
                "oid='" + oid + '\'' +
                ", programCode='" + programCode + '\'' +
                ", qty=" + qty +
                ", programFee=" + programFee +
                '}';
    }*/
}
