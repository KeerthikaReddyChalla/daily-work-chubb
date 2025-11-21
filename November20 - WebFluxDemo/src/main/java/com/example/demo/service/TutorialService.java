package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Tutorial;
import com.example.demo.repository.TutorialRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TutorialService {

    @Autowired
    TutorialRepository tutorialRepository;

    public Flux<Tutorial> findAll() {
        return tutorialRepository.findAll();
    }

    public Mono<Tutorial> save(Tutorial tutorial) {
        return tutorialRepository.save(tutorial);
    }

    public Mono<Tutorial> findById(int id) {
        return tutorialRepository.findById(id);
    }

    public Mono<Tutorial> update(int id, Tutorial tutorial) {
        return tutorialRepository.findById(id)
            .flatMap(existing -> {
                tutorial.setId(id);
                return tutorialRepository.save(tutorial);
            });
    }

    public Mono<Void> deleteById(int id) {
        return tutorialRepository.deleteById(id);
    }
}

