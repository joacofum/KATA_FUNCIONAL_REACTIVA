package com.example.demo.controllers;


import com.example.demo.models.Movie;
import com.example.demo.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/movieAPI")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/allMovies")
    public Flux<Movie> getAllMovies(){
        return movieService.findAllMovies();
    }

    @GetMapping("/getMovie/{id}")
    public Mono<Movie> getMovieById(@PathVariable("id") String MovieId){
        return movieService
                .findById(MovieId)
                .map(movie -> movie);
    }

    @PostMapping("/addMovie")
    public Mono<Movie> addMovie(@RequestBody Movie movie){
        return movieService
                .save(movie);
    }

    @PutMapping("/updateMovie/{id}")
    public Mono<Movie> updateMovie(@RequestBody Movie movie, @PathVariable("id") String movieId){
        return movieService
                .update(movie, movieId);
    }

    @DeleteMapping("/deleteMovieById/{id}")
    public Mono<Void> deleteMovieById(@PathVariable("id") String movieId){
        return movieService
                .deleteById(movieId);
    }

    @GetMapping("/getMoviesByCategory/{category}")
    public Flux<Movie> getMoviesByCategory(@PathVariable("category") String categoria){
        return movieService
                .findAllMovies()
                .filter(movie -> movie.getCategoria().equals(categoria))
                .map(movie -> movie);
    }

    @GetMapping("/getMoviesByYear/{year}")
    public Flux<Movie> getMoviesByYear(@PathVariable("year") String anio){
        return movieService
                .findAllMovies()
                .filter(movie -> movie.getAnio().equals(anio))
                .map(movie -> movie);
    }

    @GetMapping("/getBoxOfficeFromMovie/{id}")
    public Mono<String> getBoxOfficeFromMovie(@PathVariable("id") String movieId){
        return movieService
                .findById(movieId)
                .map(movie -> "La recaudación de"
                        + movie.getNombre()
                        + " fue de U$S "
                        + new BigDecimal(movie.getTaquilla() - movie.getPresupuesto()));
    }

    @GetMapping("/getBoxOfficeFromCategory/{category}")
    public Mono<String> getBoxOfficeFromCategory(@PathVariable("category") String categoria){
        Double totalTaquilla = movieService
                .findAllMovies()
                .filter(movie -> movie.getCategoria().equals(categoria))
                .collect(Collectors.summingDouble(Movie::getTaquilla))
                .block();

        Double totalPresupuesto = movieService
                .findAllMovies()
                .filter(movie -> movie.getCategoria().equals(categoria))
                .collect(Collectors.summingDouble(Movie::getPresupuesto))
                .block();

        return Mono.just("La recaudación total de las películas con categoría"
                + categoria
                + " fue de U$S "
                + new BigDecimal(totalTaquilla - totalPresupuesto));
    }

    @PutMapping("/addActorToMovie/{id}")
    public Mono<Movie> addActorToMovie(@PathVariable("id") String movieId, @RequestBody String actor){
        return movieService
                .addActorToMovie(movieId, actor);
    }

    @GetMapping("/getMoviesFromActor/{actor}")
    public Flux<String> getMoviesFromActor(@PathVariable("actor") String actor){
        return movieService
                .findAllMovies()
                .filter(movie -> movie.getActores().contains(actor))
                .map(movie -> actor + " actuó en " + movie.getNombre().concat("\n"));
    }

}
