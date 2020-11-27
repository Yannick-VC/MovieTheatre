package com.example.demo.domain;

import javax.persistence.*;
import java.sql.ClientInfoStatus;
import java.util.List;

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long genreid;
    private String genre;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "genre")
    private List<Movie> movies;

    public Genre(String genre) {
        this.genre = genre;
    }

    public Genre() {}

    public long getGenreid() {
        return genreid;
    }

    public void setGenreid(long genreid) {
        this.genreid = genreid;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Movie> getMovies() { return movies; }

    public void setMovies(List<Movie> movies) { this.movies = movies; }

    @Override
    public String toString() {
        return "Genre{" +
                "genreid=" + genreid +
                ", genre='" + genre + '\'' +
                '}';
    }
}
