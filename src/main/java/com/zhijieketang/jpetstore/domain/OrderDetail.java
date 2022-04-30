package com.zhijieketang.jpetstore.domain;

//订单明细实体类
public class OrderDetail {
    private long orderid;//订单Id
    private String productid;//商品Id
    private int quantity;//商品数量
    private double unitcost;//单价

    public long getOrderid() {
        return orderid;
    }

    public String getProductid() {
        return productid;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitcost() {
        return unitcost;
    }

    public void setOrderid(long orderid) {
        this.orderid = orderid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUnitcost(double unitcost) {
        this.unitcost = unitcost;
    }
}
