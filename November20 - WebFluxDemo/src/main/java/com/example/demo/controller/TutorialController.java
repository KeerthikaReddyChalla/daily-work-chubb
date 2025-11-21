package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Tutorial;
import com.example.demo.service.TutorialService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class TutorialController {

    @Autowired
    TutorialService tutorialService;

    @GetMapping("/tutorials")
    public Flux<Tutorial> getAll() {
        return tutorialService.findAll();
    }

    @GetMapping("/tutorials/{id}")
    public Mono<Tutorial> getById(@PathVariable int id) {
        return tutorialService.findById(id);
    }

    @PostMapping("/tutorials")
    public Mono<Integer> create(@RequestBody Tutorial t) {
        return tutorialService
            .save(new Tutorial(t.getTitle(), t.getDescription(), false))
            .map(Tutorial::getId);  
    }


    @PutMapping("/tutorials/{id}")
    public Mono<Tutorial> update(@PathVariable int id, @RequestBody Tutorial t) {
        return tutorialService.update(id, t);
    }

    @DeleteMapping("/tutorials/{id}")
    public Mono<Void> delete(@PathVariable int id) {
        return tutorialService.deleteById(id);
    }
}

