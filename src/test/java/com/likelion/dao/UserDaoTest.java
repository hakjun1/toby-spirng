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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoFactory.class)
class UserDaoTest {

    @Autowired
    ApplicationContext context;
    UserDao userDao;
    User user1;
    User user2;

    @BeforeEach
    void setUp() {
    this.userDao = context.getBean("localUserDao",UserDao.class);
    this.user1 = new User("2","hakjun","1234");
    this.user2 = new User("1","hakjun2","1234");
    }

    @Test
    void addAndget() throws SQLException {

        userDao.deleteAll();
        userDao.add(user1);
        userDao.get("2");
        assertEquals(1,userDao.getCount());

    }
    @Test
    void userNull(){
        assertThrows(EmptyResultDataAccessException.class, () -> {
            userDao.deleteAll();
            userDao.get("0");
        });
    }
    @Test
    void getAllTest() throws SQLException {
        userDao.deleteAll();
        List<User> users = userDao.getAll();
        assertEquals(0,users.size());
        userDao.add(user1);
        userDao.add(user2);
        users = userDao.getAll();
        assertEquals(2,users.size());


    }
}