package com.likelion.dao;

public class UserDaoFactory {

    public UserDao awsUserDao() {
        UserDao userDao = new UserDao(new AwsConnectionMaker());
        return userDao;
    }

    public UserDao localUserDao() {
        UserDao userDao = new UserDao(new LocalConnectionMaker());
        return userDao;
    }
}
