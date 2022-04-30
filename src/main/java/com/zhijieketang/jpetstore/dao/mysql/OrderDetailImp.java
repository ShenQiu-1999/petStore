package com.zhijieketang.jpetstore.dao.mysql;

import com.zhijieketang.jpetstore.dao.OrderDetailDao;
import com.zhijieketang.jpetstore.domain.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailImp implements OrderDetailDao {

    @Override
    public List<OrderDetail> findAll() {
        return null;
    }

    @Override
    public OrderDetail findByPK(int orderid, String productid) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        OrderDetail orderDetail = null;
        try {
            conn = DBHelper.getConnection();
            String sql = "select orderid,productid,quantity,unitcost from orderdetails where orderid = ? and productid = ?";
            pstmt = conn.prepareStatement(sql);
            //参数绑定
            pstmt.setInt(1, orderid);
            pstmt.setString(2, productid);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                orderDetail = new OrderDetail();
                orderDetail.setOrderid(rs.getInt("orderid"));
                orderDetail.setProductid(rs.getString("productid"));
                orderDetail.setQuantity(rs.getInt("quantity"));
                orderDetail.setUnitcost(rs.getDouble("unitcost"));

                return orderDetail;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
    public int create(OrderDetail orderDetail) {
        try {
            Connection conn = DBHelper.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("insert into orderdetails (orderid, productid, quantity, unitcost) values (?,?,?,?)");

            pstmt.setLong(1, orderDetail.getOrderid());
            pstmt.setString(2, orderDetail.getProductid());
            pstmt.setInt(3, orderDetail.getQuantity());
            pstmt.setDouble(4, orderDetail.getUnitcost());

            int affectedRows = pstmt.executeUpdate();
            System.out.printf("成功插入%d条数据.\n", affectedRows);

        } catch (SQLException e) {
            return -1;
        }
        return 0;
    }

    @Override
    public int modify(OrderDetail orderDetail) {
        return 0;
    }

    @Override
    public int remove(OrderDetail orderDetail) {
        return 0;
    }
}
