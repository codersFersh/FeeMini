package com.example.feeend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Pay {
    private Integer id;
    private String label;
    private Double payout;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date costtime;


    public Pay() {
    }

    public Pay(Integer id, String label, Double payout, Date costtime) {
        this.id = id;
        this.label = label;
        this.payout = payout;
        this.costtime = costtime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getPayout() {
        return payout;
    }

    public void setPayout(Double payout) {
        this.payout = payout;
    }

    public Date getCosttime() {
        return costtime;
    }

    public void setCosttime(Date costtime) {
        this.costtime = costtime;
    }

    @Override
    public String toString() {
        return "Pay{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", payout=" + payout +
                ", costtime=" + costtime +
                '}';
    }
}
