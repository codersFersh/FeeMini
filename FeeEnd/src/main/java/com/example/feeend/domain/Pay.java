package com.example.feeend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Pay {
    private Integer id;
    private Integer incomeid;
    private String item;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date costtime;
    private Double payout;

    private Integer units;

    private Double unitprice;

    private String approver;

    private Integer type;

    private  String  notes;


    public Pay() {
    }

    public Pay(Integer id, Integer incomeid, String item, Date costtime, Double payout, Integer units, Double unitprice, String approver, Integer type, String notes) {
        this.id = id;
        this.incomeid = incomeid;
        this.item = item;
        this.costtime = costtime;
        this.payout = payout;
        this.units = units;
        this.unitprice = unitprice;
        this.approver = approver;
        this.type = type;
        this.notes = notes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIncomeid() {
        return incomeid;
    }

    public void setIncomeid(Integer incomeid) {
        this.incomeid = incomeid;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Date getCosttime() {
        return costtime;
    }

    public void setCosttime(Date costtime) {
        this.costtime = costtime;
    }

    public Double getPayout() {
        return payout;
    }

    public void setPayout(Double payout) {
        this.payout = payout;
    }

    public Integer getUnits() {
        return units;
    }

    public void setUnits(Integer units) {
        this.units = units;
    }

    public Double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(Double unitprice) {
        this.unitprice = unitprice;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Pay{" +
                "id=" + id +
                ", incomeid=" + incomeid +
                ", item='" + item + '\'' +
                ", costtime=" + costtime +
                ", payout=" + payout +
                ", units=" + units +
                ", unitprice=" + unitprice +
                ", approver='" + approver + '\'' +
                ", type=" + type +
                ", notes='" + notes + '\'' +
                '}';
    }
}
