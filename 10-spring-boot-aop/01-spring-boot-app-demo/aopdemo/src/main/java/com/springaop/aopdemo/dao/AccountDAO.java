package com.springaop.aopdemo.dao;

import com.springaop.aopdemo.Account;

public interface AccountDAO {
    void addAccount(Account theAccount, boolean vipFlag);
    boolean doWork();
}
