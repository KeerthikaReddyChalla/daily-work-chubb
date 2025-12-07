package com.example.reactive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.reactive.model.Tutorial;
import com.example.reactive.repository.TutorialRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TutorialService {

    @Autowired
    private TutorialRepository repo;

    public Flux<Tutorial> findAll() {
        return repo.findAll();
    }

    public Mono<Tutorial> findById(String id) {
        return repo.findById(id);
    }

    public Mono<Tutorial> save(Tutorial tutorial) {
        return repo.save(tutorial);
    }

    public Mono<Tutorial> update(String id, Tutorial tutorial) {
        return repo.findById(id)
                .flatMap(existing -> {
                    existing.setTitle(tutorial.getTitle());
                    existing.setDescription(tutorial.getDescription());
                    existing.setPublished(tutorial.isPublished());
                    return repo.save(existing);
                })
                .switchIfEmpty(Mono.error(new RuntimeException("Tutorial Not Found")));
    }


    public Mono<Void> deleteById(String id) {
        return repo.deleteById(id);
    }

    public Mono<Void> deleteAll() {
        return repo.deleteAll();
    }

    public Flux<Tutorial> findByPublished(boolean published) {
        return repo.findByPublished(published);
    }

    public Flux<Tutorial> findByTitleContaining(String title) {
        return repo.findByTitleContaining(title);
    }
}
