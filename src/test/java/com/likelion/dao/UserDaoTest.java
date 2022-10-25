package com.likelion.dao;

import com.likelion.domain.User;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    @Test
    void addAndget() throws SQLException {
        UserDao userDao = new UserDaoFactory().localUserDao();
        userDao.deleteAll();
        userDao.add(new User("1","hakjun","1234"));
        User user = userDao.get("1");
        assertEquals(1,userDao.getCount());

    }
}