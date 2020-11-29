package com.example.demo.controller;

import com.example.demo.domain.Movie;
import com.example.demo.domain.MovieDAO;
import com.example.demo.domain.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class MovieController {

    @Autowired
    private MovieDAO movieDAO;

    @RequestMapping(value= "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = {"/", "/movielist"})
    public String MovieList(Model model) {
        List<Movie> movies = movieDAO.findAll();
        model.addAttribute("movies", movies);
        return "movielist";
    }

    @RequestMapping(value = "/buy/{id}", method = RequestMethod.GET)
    public String BuyTicket(@PathVariable("id") int id, Model model) {
        Movie movie = movieDAO.findOne(id);
        model.addAttribute("movie", movie);
        return "buy";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/add")
    public String AddMovie(Model model) {
        model.addAttribute("movie", new Movie());
        return "addmovie";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String Save(Movie movie) {
        movieDAO.save(movie);
        return "redirect:movielist";
    }

    @RequestMapping(value = "/bought")
    public String Bought() {
        return "/bought";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String Delete(@PathVariable("id") int id, Model model) {
        movieDAO.delete(id);
        return "redirect:../movielist";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
    public String ModifyMovie(@PathVariable("id") int id, Model model) {
        Movie movie = movieDAO.findOne(id);
        model.addAttribute("movie", movie);
        return "modify";
    }

    // REST get all movies
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value="/movies", method = RequestMethod.GET)
    public @ResponseBody List<Movie> MovieListREST() {
        return (List<Movie>) movieDAO.findAll();
    }

    // REST get movie on ID
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value="/movielist/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Movie> findMovieOptional(@PathVariable("id") int id) {
        return Optional.ofNullable(movieDAO.findOne(id));
    }
}