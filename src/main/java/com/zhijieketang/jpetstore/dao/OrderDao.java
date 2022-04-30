package com.zhijieketang.jpetstore.dao;

import com.zhijieketang.jpetstore.domain.Order;

import java.util.List;

public interface OrderDao {
    abstract List<Order> findAll();

    abstract Order findById(int orderid);

    abstract int create(Order order);

    abstract int modify(Order order);

    abstract int remove(Order order);
}
