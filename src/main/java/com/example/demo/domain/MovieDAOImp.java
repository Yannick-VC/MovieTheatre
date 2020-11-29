package com.example.demo.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieDAOImp implements MovieDAO{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Movie movie){
        String sql = "insert into movies(name, duration, rating) values(?,?,?)";
        Object[] parameters = new Object[] { movie.getName(), movie.getDuration(),
                movie.getRating()};
        jdbcTemplate.update(sql, parameters);
    }

    public void update(Movie movie){
        String sql = "UPDATE movies set name = ? , duration = ? , rating = ? where movieid = ?";
        Object[] parameters = new Object[] { movie.getName(), movie.getDuration(),
                movie.getRating(), movie.getId()};
        jdbcTemplate.update(sql, parameters);

    }

    public void delete(int id){
        String sql = "delete from movies where movieid = ?";
        Object[] parameters = new Object[] { id };
        jdbcTemplate.update(sql, parameters);

    }

    //Find one allows me to reduce code in other functions
    //As this functionnality is often used, I created it and call it from other functions
    public Movie findOne(int id) {
        String sql = "select movieid, name, duration, rating from movies where movieid = ?";
        Object[] parameters = new Object[] { id };
        RowMapper<Movie> mapper = new MovieRowMapper();

        Movie movie = jdbcTemplate.queryForObject(sql, parameters, mapper);
        return movie;
    }

    //Find all is used to display all the products on a page
    public List<Movie> findAll() {
        String sql = "select * from movies";
        RowMapper<Movie> mapper = new MovieRowMapper();
        List<Movie> movies = jdbcTemplate.query(sql, mapper);
        return movies;
    }
}
