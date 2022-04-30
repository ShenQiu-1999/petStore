package com.zhijieketang.jpetstore.dao;

import com.zhijieketang.jpetstore.domain.OrderDetail;

import java.util.List;

public interface OrderDetailDao {
    abstract List<OrderDetail> findAll();

    abstract OrderDetail findByPK(int orderid, String productid);

    abstract int create(OrderDetail orderDetail);

    abstract int modify(OrderDetail orderDetail);

    abstract int remove(OrderDetail orderDetail);
}
