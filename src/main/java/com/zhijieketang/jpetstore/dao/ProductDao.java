package com.zhijieketang.jpetstore.dao;

import com.zhijieketang.jpetstore.domain.Product;

import java.util.List;

public interface ProductDao {
    abstract List<Product> findAll();

    abstract List<Product> findByCategory(String category);

    abstract Product findById(String productid);

    abstract int create(Product product);

    abstract int modify(Product product);

    abstract int remove(Product product);
}
