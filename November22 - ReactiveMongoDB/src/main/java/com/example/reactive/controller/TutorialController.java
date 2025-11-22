package com.example.reactive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.reactive.model.Tutorial;
import com.example.reactive.service.TutorialService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class TutorialController {

    @Autowired
    private TutorialService service;

    // GET all or filter by title
    @GetMapping("/tutorials")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Tutorial> getAll(@RequestParam(required = false) String title) {
        return (title == null) ? service.findAll() : service.findByTitleContaining(title);
    }

    // GET by id
    @GetMapping("/tutorials/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Tutorial> getById(@PathVariable String id) {
        return service.findById(id);
    }

    // CREATE
    @PostMapping("/tutorials")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Tutorial> create(@RequestBody Tutorial tutorial) {
        return service.save(tutorial);
    }

    // UPDATE
    @PutMapping("/tutorials/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Tutorial> update(@PathVariable String id, @RequestBody Tutorial tutorial) {
        return service.update(id, tutorial);
    }

    // DELETE one
    @DeleteMapping("/tutorials/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String id) {
        return service.deleteById(id);
    }

    // DELETE all
    @DeleteMapping("/tutorials")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteAll() {
        return service.deleteAll();
    }

    // GET published tutorials
    @GetMapping("/tutorials/published")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Tutorial> getPublished() {
        return service.findByPublished(true);
    }
}
