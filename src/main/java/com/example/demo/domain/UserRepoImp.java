package com.example.demo.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepoImp implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Simple function to get all of the information of a user
    @Override
    public User findByUsername(String username) {
        String sql = "select id, username, passwordHash, role, email from users where username = ? LIMIT 1";
        Object[] parameters = new Object[] { username };
        RowMapper<User> mapper = new UserRowMapper();

        User user = jdbcTemplate.queryForObject(sql, parameters, mapper);
        return user;
    }

    //Saving a user and inserting the user into the table "users"
    public void save(User user) {
        String sql = "insert into users(username, passwordHash, role, email) values(?,?,?,?)";
        Object[] parameters = new Object[]{user.getUsername(), user.getPasswordHash(),
                user.getRole(), user.getEmail()};
        jdbcTemplate.update(sql, parameters);
    }
}
