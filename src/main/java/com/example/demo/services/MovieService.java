package com.example.demo.services;

import com.example.demo.models.Movie;
import com.example.demo.repositories.MovieReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MovieService implements InterfaceMovieService{
    @Autowired
    private MovieReactiveRepository repository;

    @Override
    public Flux<Movie> findAllMovies() {
        return repository.findAll();
    }

    @Override
    public Mono<Movie> findById(String MovieId) {
        return repository.findById(MovieId);
    }

    @Override
    public Mono<Movie> save(Movie movie) {
        return repository.save(movie);
    }

    @Override
    public Mono<Movie> update(Movie movie, String movieId) {
        return repository.findById(movieId).flatMap(movie1 -> {movie1.setId(movieId);
            return save(movie);
        });
    }

    @Override
    public Mono<Void> deleteById(String MovieId) {
        return repository.deleteById(MovieId);
    }
}
