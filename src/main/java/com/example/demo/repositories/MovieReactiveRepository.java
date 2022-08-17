package com.example.demo.repositories;

import com.example.demo.models.Movie;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieReactiveRepository extends ReactiveMongoRepository<Movie, String> {
}
