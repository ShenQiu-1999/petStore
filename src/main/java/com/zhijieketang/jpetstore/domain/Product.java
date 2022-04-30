package com.zhijieketang.jpetstore.domain;

public class Product {
    private String productid;//商品Id
    private String category;//商品类别
    private String cname;//商品中文名
    private String ename;//商品英文名
    private String image;//商品图片
    private String descn;//商品描述
    private double listprice;//商品市场价
    private double unitcost;//商品单价

    public void setProductid(String product) {
        this.productid = product;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescn(String descn) {
        this.descn = descn;
    }

    public void setListprice(double listprice) {
        this.listprice = listprice;
    }

    public void setUnitcost(double unitcost) {
        this.unitcost = unitcost;
    }

    public String getProductid() {
        return productid;
    }

    public String getCategory() {
        return category;
    }

    public String getCname() {
        return cname;
    }

    public String getEname() {
        return ename;
    }

    public String getImage() {
        return image;
    }

    public String getDescn() {
        return descn;
    }

    public double getListprice() {
        return listprice;
    }

    public double getUnitcost() {
        return unitcost;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productid='" + productid + '\'' +
                ", category='" + category + '\'' +
                ", cname='" + cname + '\'' +
                ", ename='" + ename + '\'' +
                ", image='" + image + '\'' +
                ", descn='" + descn + '\'' +
                ", listprice=" + listprice +
                ", unitcost=" + unitcost +
                '}';
    }
}
