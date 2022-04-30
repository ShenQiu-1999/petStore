package com.zhijieketang.jpetstore.dao;

import com.zhijieketang.jpetstore.domain.Account;

import java.util.List;

public interface AccountDao {
    abstract List<Account> findAll();

    abstract Account findById(String userid);

    abstract int create(Account account);

    abstract int modify(Account account);

    abstract int remove(Account account);
}
