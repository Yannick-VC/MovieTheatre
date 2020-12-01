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

    //Login page display
    @RequestMapping(value= "/login")
    public String login() {
        return "login";
    }

    //Returning the list of all movies with their data
    @RequestMapping(value = {"/", "/movielist"})
    public String MovieList(Model model) {
        List<Movie> movies = movieDAO.findAll();
        model.addAttribute("movies", movies);
        return "movielist";
    }

    //Buying of movietickets by their ID
    @RequestMapping(value = "/buy/{id}", method = RequestMethod.GET)
    public String BuyTicket(@PathVariable("id") int id, Model model) {
        Movie movie = movieDAO.findOne(id);
        model.addAttribute("movie", movie);
        return "buy";
    }

    //Adding a movie if the person has the required authorization
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/add")
    public String AddMovie(Model model) {
        model.addAttribute("movie", new Movie());
        return "addmovie";
    }

    //Calling the update function to modify a movie
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String Save(Movie movie) {
        movieDAO.update(movie);
        return "redirect:movielist";
    }

    //Adding a movie to the list by calling the save function.
    @RequestMapping(value = "/adding", method = RequestMethod.POST)
    public String AddMovie(Movie movie) {
        movieDAO.save(movie);
        return "redirect:movielist";
    }

    //Returning the bought page
    @RequestMapping(value = "/bought")
    public String Bought() {
        return "/bought";
    }

    //Deleting the selected movie if the user has the authority to do so.
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String Delete(@PathVariable("id") int id, Model model) {
        movieDAO.delete(id);
        return "redirect:../movielist";
    }

    //Modifying the movie by it's ID
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