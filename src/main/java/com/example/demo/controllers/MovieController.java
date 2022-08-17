package com.example.demo.controllers;


import com.example.demo.models.Movie;
import com.example.demo.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movieAPI")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/allMovies")
    public ResponseEntity<Flux<Movie>> getAllMovies(){
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(movieService.findAllMovies());
    }

    @GetMapping("/getMovie/{id}")
    public Mono<ResponseEntity<Movie>> getMovieById(@PathVariable("id") String MovieId){
        return movieService
                .findById(MovieId)
                .map(movie -> ResponseEntity
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(movie)
                );
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
        return movieService.deleteById(movieId);
    }

}
