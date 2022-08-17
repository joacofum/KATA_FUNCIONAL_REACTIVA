package com.example.demo.services;

import com.example.demo.models.Movie;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface InterfaceMovieService {
    Flux<Movie> findAllMovies();
    Mono<Movie> findById(String MovieId);
    Mono<Movie> save(Movie movie);
    Mono<Movie> update(Movie movie, String movieId);
    Mono<Void> deleteById(String MovieId);
}
