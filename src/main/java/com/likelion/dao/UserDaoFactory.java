package com.likelion.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
public class UserDaoFactory {

    @Bean
    public UserDao awsUserDao() {
        UserDao userDao = new UserDao(awsdataSource());
        return userDao;
    }

    @Bean
    public UserDao localUserDao() {
        UserDao userDao = new UserDao(localdataSource());
        return userDao;
    }

    @Bean
    public DataSource awsdataSource() {
        Map<String,String> env = System.getenv();
        SimpleDriverDataSource simpleDriverDataSource = new SimpleDriverDataSource();
        simpleDriverDataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
        simpleDriverDataSource.setUrl(env.get("DB_HOST"));
        simpleDriverDataSource.setUsername(env.get("DB_USER"));
        simpleDriverDataSource.setPassword(env.get("DB_PASSWORD"));

        return simpleDriverDataSource;
    }

    @Bean
    public DataSource localdataSource() {
        Map<String,String> env = System.getenv();
        SimpleDriverDataSource simpleDriverDataSource = new SimpleDriverDataSource();
        simpleDriverDataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
        simpleDriverDataSource.setUrl(env.get("DB_HOST"));
        simpleDriverDataSource.setUsername(env.get("DB_USER"));
        simpleDriverDataSource.setPassword(env.get("DB_PASSWORD"));

        return simpleDriverDataSource;
    }


}
