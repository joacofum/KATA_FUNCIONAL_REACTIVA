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
    public Mono<Movie> findById(String movieId) {
        return repository.findById(movieId);
    }

    @Override
    public Mono<Movie> save(Movie movie) {
        return repository.save(movie);
    }

    @Override
    public Mono<Movie> update(Movie movie, String movieId) {
        return repository
                .findById(movieId)
                .map(movie1 -> {
                    movie1.setId(movieId);
                    movie1.setNombre(movie.getNombre());
                    movie1.setActores(movie.getActores());
                    movie1.setIdioma(movie.getIdioma());
                    movie1.setCategoria(movie.getCategoria());
                    movie1.setAnio(movie.getAnio());
                    return movie1;
                })
                .flatMap(this::save);
    }

    @Override
    public Mono<Void> deleteById(String movieId) {
        return repository
                .findById(movieId)
                .flatMap(movie -> repository.deleteById(movie.getId()));
    }

}
