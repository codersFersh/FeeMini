package com.example.feeend.domain;

public class Details {
    private Integer id;
    private Integer incomeid;
    private String entry;
    private String cashpay;
    private String wechat;
    private String alipay;
    private String unpaid;


    public Details() {
    }

    public Details(Integer id, Integer incomeid, String entry, String cashpay, String wechat, String alipay, String unpaid) {
        this.id = id;
        this.incomeid = incomeid;
        this.entry = entry;
        this.cashpay = cashpay;
        this.wechat = wechat;
        this.alipay = alipay;
        this.unpaid = unpaid;
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

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getCashpay() {
        return cashpay;
    }

    public void setCashpay(String cashpay) {
        this.cashpay = cashpay;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    public String getUnpaid() {
        return unpaid;
    }

    public void setUnpaid(String unpaid) {
        this.unpaid = unpaid;
    }

    @Override
    public String toString() {
        return "Details{" +
                "id=" + id +
                ", incomeid=" + incomeid +
                ", entry='" + entry + '\'' +
                ", cashpay='" + cashpay + '\'' +
                ", wechat='" + wechat + '\'' +
                ", alipay='" + alipay + '\'' +
                ", unpaid='" + unpaid + '\'' +
                '}';
    }
}
