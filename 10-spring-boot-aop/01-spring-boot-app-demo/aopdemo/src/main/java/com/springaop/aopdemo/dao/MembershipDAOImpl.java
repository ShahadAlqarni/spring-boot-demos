package com.springaop.aopdemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipDAOImpl implements MembershipDAO{
    @Override
    public String addMembership() {

        System.out.println(getClass() + " DOING MY DB WORK: ADDING AN ACCOUNT");
        return "!";

    }

    @Override
    public void goToSleep() {
        System.out.println(getClass() + "Go To Sleep");
    }
}
