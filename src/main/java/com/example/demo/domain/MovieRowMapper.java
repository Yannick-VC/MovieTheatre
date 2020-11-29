package com.example.demo.domain;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieRowMapper implements RowMapper<Movie> {

    public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
        Movie movie = new Movie();
        movie.setId(rs.getLong("movieid"));
        movie.setName(rs.getString("name"));
        movie.setDuration(rs.getInt("duration"));
        movie.setRating(rs.getInt("rating"));
        return movie;
    }
}
