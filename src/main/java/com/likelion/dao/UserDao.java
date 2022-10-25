package com.likelion.dao;

import com.likelion.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private ConnectionMaker connectionMaker;

    public UserDao() {
    }

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void jdbcContextWithStatmentStrategy(StatementStrategy statementStrategy) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = connectionMaker.makeConnection();
            ps = statementStrategy.makePreParedStatment(c);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public void add(User user) throws SQLException {
       jdbcContextWithStatmentStrategy(new AddStrategy(user));
    }

    public User get(String id) throws SQLException {
        Connection c = connectionMaker.makeConnection();
        PreparedStatement ps = c.prepareStatement("SELECT*FROM users WHERE id =?");
        ps.setString(1,id);

        ResultSet rs = ps.executeQuery();
        User user = null;
        if (rs.next()) {
           // rs.next();
            user = new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));
        }
        rs.close();
        ps.close();
        c.close();


        if (user==null) {
            throw new EmptyResultDataAccessException(1);
        }
        return user;
    }

    public void deleteAll() throws SQLException {
        jdbcContextWithStatmentStrategy(new DeleteAllStrategy());
    }
    public int getCount() throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = connectionMaker.makeConnection();
            ps = c.prepareStatement("SELECT count(*) from users");
            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);

        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                }
            }
        }
    }
}
