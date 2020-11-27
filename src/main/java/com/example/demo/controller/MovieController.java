package com.example.demo.controller;

import com.example.demo.domain.GenreRepository;
import com.example.demo.domain.Movie;
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
    private MovieRepository MRepo;

    @Autowired
    private GenreRepository GRepo;

    @RequestMapping(value= "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = {"/", "/movielist"})
    public String MovieList(Model model) {
        model.addAttribute("movies", MRepo.findAll());
        model.addAttribute("genres", GRepo.findAll());
        return "movielist";
    }

    @RequestMapping(value = "/buy/{id}", method = RequestMethod.GET)
    public String BuyTicket(@PathVariable("id") Long id, Model model) {
        model.addAttribute("movie", MRepo.findById(id));
        return "buy";
    }

    @RequestMapping(value = "/add")
    public String AddMovie(Model model) {
        model.addAttribute("movie", new Movie());
        model.addAttribute("genre", GRepo.findAll());
        return "addmovie";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String Save(Movie movie) {
        MRepo.save(movie);
        return "redirect:movielist";
    }

    @RequestMapping(value = "/bought")
    public String Bought() {
        return "/bought";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String Delete(@PathVariable("id") Long id, Model model) {
        MRepo.deleteById(id);
        return "redirect:../movielist";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
    public String ModifyMovie(@PathVariable("id") Long id, Model model) {
        model.addAttribute("movie", MRepo.findById(id));
        model.addAttribute("genre", GRepo.findAll());
        return "modify";
    }

    // REST get all movies
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value="/movies", method = RequestMethod.GET)
    public @ResponseBody List<Movie> MovieListREST() {
        System.out.print((List<Movie>) MRepo.findAll());
        return (List<Movie>) MRepo.findAll();
    }

    // REST get movie on ID
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value="/movielist/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Movie> findMovieOptional(@PathVariable("id") Long id) {
        return MRepo.findById(id);
    }
}