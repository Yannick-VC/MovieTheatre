package com.example.demo.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    //When displaying products, I can map the database rows to the displayed rows
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setPasswordHash(rs.getString("passwordhash"));
        user.setRole(rs.getString("role"));
        user.setEmail(rs.getString("email"));
        return user;
    }
}
