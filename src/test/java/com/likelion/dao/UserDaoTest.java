package com.likelion.dao;

import com.likelion.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoFactory.class)
class UserDaoTest {

    @Autowired
    ApplicationContext context;
    UserDao userDao;
    User user1;

    @BeforeEach
    void setUp() {
    this.userDao = context.getBean("localUserDao",UserDao.class);
    this.user1 = new User("1","hakjun","1234");
    }

    @Test
    void addAndget() throws SQLException {

        userDao.deleteAll();
        userDao.add(user1);
        userDao.get("1");
        assertEquals(1,userDao.getCount());

    }
    @Test
    void userNull(){
        assertThrows(EmptyResultDataAccessException.class, () -> {
            userDao.deleteAll();
            userDao.get("0");
        });
    }
}