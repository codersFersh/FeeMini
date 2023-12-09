package com.example.feeend.domain;

import java.util.Date;

public class Pay {
    private Integer id;
    private String explain;
    private Double payout;
    private Date costtime;


    public Pay() {
    }

    public Pay(Integer id, String explain, Double payout, Date costtime) {
        this.id = id;
        this.explain = explain;
        this.payout = payout;
        this.costtime = costtime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
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
                ", explain='" + explain + '\'' +
                ", payout=" + payout +
                ", costtime=" + costtime +
                '}';
    }
}
