package com.example.feeend.domain;

import java.util.Date;

public class Income {
    private Integer id;
    private String title;
    private Date origindate;
    private Double budget;
    private Date confirmdate;
    private Double receipt;

    public Income() {
    }

    public Income(Integer id, String title, Date origindate, Double budget, Date confirmdate, Double receipt) {
        this.id = id;
        this.title = title;
        this.origindate = origindate;
        this.budget = budget;
        this.confirmdate = confirmdate;
        this.receipt = receipt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getOrigindate() {
        return origindate;
    }

    public void setOrigindate(Date origindate) {
        this.origindate = origindate;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public Date getConfirmdate() {
        return confirmdate;
    }

    public void setConfirmdate(Date confirmdate) {
        this.confirmdate = confirmdate;
    }

    public Double getReceipt() {
        return receipt;
    }

    public void setReceipt(Double receipt) {
        this.receipt = receipt;
    }

    @Override
    public String toString() {
        return "Income{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", origindate=" + origindate +
                ", budget=" + budget +
                ", confirmdate=" + confirmdate +
                ", receipt=" + receipt +
                '}';
    }
}



