package com.zhijieketang.jpetstore.domain;

import java.util.Date;

public class Order {
    private long orderid;//订单Id
    private String userid;//下订单的用户Id
    private Date orderdate;//下订单时间
    private int status;//订单付款状态 0 待付款 1 已付款
    private double amount;//订单应付金额

    public void setOrderid(long orderid) {
        this.orderid = orderid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getOrderid() {
        return orderid;
    }

    public String getUserid() {
        return userid;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public int getStatus() {
        return status;
    }

    public double getAmount() {
        return amount;
    }
}
