package view.tm;

import java.math.BigDecimal;

public class PlaceProgramDetailTM {
    private String code;
    private String description;
    private int qty;
    private BigDecimal programFee;
    private BigDecimal total;

    public PlaceProgramDetailTM() {
    }

    public PlaceProgramDetailTM(String code, String description, int qty, BigDecimal programFee, BigDecimal total) {
        this.setCode(code);
        this.setDescription(description);
        this.setQty(qty);
        this.setProgramFee(programFee);
        this.setTotal(total);
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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderDetailTM{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", qty=" + qty +
                ", programFee=" + programFee +
                ", total=" + total +
                '}';
    }
}
