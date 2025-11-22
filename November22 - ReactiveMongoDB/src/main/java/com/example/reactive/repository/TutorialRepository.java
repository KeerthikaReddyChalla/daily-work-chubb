package com.example.reactive.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.example.reactive.model.Tutorial;
import reactor.core.publisher.Flux;

public interface TutorialRepository extends ReactiveMongoRepository<Tutorial, String> {

    Flux<Tutorial> findByPublished(boolean published);

    Flux<Tutorial> findByTitleContaining(String title);
}
