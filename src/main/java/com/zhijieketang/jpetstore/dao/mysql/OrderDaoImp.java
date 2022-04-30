package com.zhijieketang.jpetstore.dao.mysql;

import com.zhijieketang.jpetstore.dao.OrderDao;
import com.zhijieketang.jpetstore.domain.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDaoImp implements OrderDao {

    @Override
    public List<Order> findAll() {
        String sql = "select orderid,userid,orderdate from orders";
        ArrayList<Order> orderList = new ArrayList<>();
        try {
            Connection conn = DBHelper.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setOrderid(rs.getInt("orderid"));
                order.setUserid(rs.getString("userid"));
                order.setOrderdate(rs.getDate("orderdate"));

                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    public Order findById(int orderid) {


        return null;
    }

    @Override
    public int create(Order order) {
        try {
            Connection conn = DBHelper.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("insert into orders (orderid, userid, orderdate,status, amout)" + "values (?,?,?,?,?)");
            //绑定参数
            pstmt.setLong(1, order.getOrderid());
            pstmt.setString(2, order.getUserid());
            Date date = new Date();
            //pstmt.setDate(3, new java.sql.Date(date.getTime()));
            pstmt.setTimestamp(3, new Timestamp(date.getTime()));
            pstmt.setInt(4, order.getStatus());
            pstmt.setDouble(5, order.getAmount());

            int affectedRows = pstmt.executeUpdate();
            System.out.printf("成功插入%d条数据.\n", affectedRows);
        } catch (SQLException e) {
            return -1;
        }
        return 0;
    }

    @Override
    public int modify(Order order) {
        return 0;
    }

    @Override
    public int remove(Order order) {
        return 0;
    }
}
