package com.zhijieketang.jpetstore.dao.mysql;

import com.zhijieketang.jpetstore.dao.ProductDao;
import com.zhijieketang.jpetstore.domain.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImp implements ProductDao {
    @Override
    public List<Product> findAll() {
        String sql = "select productid,category,cname,ename,image," +
                "listprice,unitcost,descn from products";
        List<Product> products = new ArrayList<>();
        try {
            //2.创建数据库连接
            Connection conn = DBHelper.getConnection();
            //3.创建语句对象
            PreparedStatement pstmt = conn.prepareStatement(sql);
            //4.绑定参数
            //5.执行查询（R）
            ResultSet rs = pstmt.executeQuery();
            //6.遍历结果集
            while (rs.next()) {
                Product p = new Product();
                p.setProductid(rs.getString("productid"));
                p.setCategory(rs.getString("category"));
                p.setCname(rs.getString("cname"));
                p.setEname(rs.getString("ename"));
                p.setImage(rs.getString("image"));
                p.setListprice(rs.getDouble("listprice"));
                p.setUnitcost(rs.getDouble("unitcost"));
                p.setDescn(rs.getString("descn"));

                products.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> findByCategory(String category) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Product> products = new ArrayList<>();
        try {
            conn = DBHelper.getConnection();
            String sql = "select productid,category,cname,ename,image," +
                    "listprice,unitcost,descn from products where category = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, category);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductid(rs.getString("productid"));
                p.setCategory(rs.getString("category"));
                p.setCname(rs.getString("cname"));
                p.setEname(rs.getString("ename"));
                p.setImage(rs.getString("image"));
                p.setListprice(rs.getDouble("listprice"));
                p.setUnitcost(rs.getDouble("unitcost"));
                p.setDescn(rs.getString("descn"));

                products.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {//释放资源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return products;
    }

    @Override
    public Product findById(String productid) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBHelper.getConnection();
            String sql = "select productid,category,cname,ename,image," +
                    "listprice,unitcost,descn from products where productid = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, productid);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductid(rs.getString("productid"));
                p.setCategory(rs.getString("category"));
                p.setCname(rs.getString("cname"));
                p.setEname(rs.getString("ename"));
                p.setImage(rs.getString("image"));
                p.setListprice(rs.getDouble("listprice"));
                p.setUnitcost(rs.getDouble("unitcost"));
                p.setDescn(rs.getString("descn"));

                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {//释放资源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return null;
    }

    @Override
    public int create(Product product) {
        return 0;
    }

    @Override
    public int modify(Product product) {
        return 0;
    }

    @Override
    public int remove(Product product) {
        return 0;
    }
}
