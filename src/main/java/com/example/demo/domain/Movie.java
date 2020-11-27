package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private int duration;
    private int rating;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "genreid")
    private Genre genre;

    public Movie(String name, int duration, int rating, Genre genre) {
        super();
        this.name = name;
        this.duration = duration;
        this.rating = rating;
        this.genre = genre;
    }

    public Movie() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {return rating; }

    public void setRating(int rating) { this.rating = rating; }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }


    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", rating=" + rating +
                ", genre=" + genre +
                '}';
    }
}
