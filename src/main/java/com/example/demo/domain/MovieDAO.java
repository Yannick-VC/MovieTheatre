package com.example.demo.domain;

import java.util.List;

public interface MovieDAO {
    public void save(Movie movie);

    public void update(Movie movie);

    public Movie findOne(int id);

    public void delete(int id);

    public List<Movie> findAll();
}

