package com.example.feeend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Income {
    private Integer id;
    private String title;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date origindate;
    private Double budget;
    private Double receipt;

    private Integer classsize;

    private Integer paysize;
    private Integer status;
    private Integer state;

    private String descr;





    public Income() {
    }

    public Income(Integer id, String title, Date origindate, Double budget, Double receipt, Integer classsize, Integer paysize, Integer status, Integer state, String descr) {
        this.id = id;
        this.title = title;
        this.origindate = origindate;
        this.budget = budget;
        this.receipt = receipt;
        this.classsize = classsize;
        this.paysize = paysize;
        this.status = status;
        this.state = state;
        this.descr = descr;
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

    public Double getReceipt() {
        return receipt;
    }

    public void setReceipt(Double receipt) {
        this.receipt = receipt;
    }

    public Integer getClasssize() {
        return classsize;
    }

    public void setClasssize(Integer classsize) {
        this.classsize = classsize;
    }

    public Integer getPaysize() {
        return paysize;
    }

    public void setPaysize(Integer paysize) {
        this.paysize = paysize;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Override
    public String toString() {
        return "Income{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", origindate=" + origindate +
                ", budget=" + budget +
                ", receipt=" + receipt +
                ", classsize=" + classsize +
                ", paysize=" + paysize +
                ", status=" + status +
                ", state=" + state +
                ", descr='" + descr + '\'' +
                '}';
    }
}



