package com.example.feeend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Pay {
    private Integer id;
    private String item;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date costtime;
    private Double payout;
    private  String  notes;


    public Pay() {
    }

    public Pay(Integer id, String item, Date costtime, Double payout, String notes) {
        this.id = id;
        this.item = item;
        this.costtime = costtime;
        this.payout = payout;
        this.notes = notes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
                ", item='" + item + '\'' +
                ", costtime=" + costtime +
                ", payout=" + payout +
                ", notes='" + notes + '\'' +
                '}';
    }
}
