package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @ResponseStatus(HttpStatus.OK)
    public Flux<Tutorial> getAll() {
        return tutorialService.findAll();
    }

    @GetMapping("/tutorials/{id}")
    public Mono<ResponseEntity<Tutorial>> getById(@PathVariable int id) {
        return tutorialService.findById(id)
            .map(tutorial -> ResponseEntity.ok(tutorial)) // 200
            .defaultIfEmpty(ResponseEntity.notFound().build()); // 404
    }


    @PostMapping("/tutorials")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Integer> create(@RequestBody Tutorial t) {
        return tutorialService
            .save(new Tutorial(t.getTitle(), t.getDescription(), false))
            .map(Tutorial::getId); // return only id
    }


    @PutMapping("/tutorials/{id}")
    public Mono<ResponseEntity<Tutorial>> update(
            @PathVariable int id,
            @RequestBody Tutorial t) {

        return tutorialService.update(id, t)
            .map(updated -> ResponseEntity.ok(updated)) // 200
            .defaultIfEmpty(ResponseEntity.notFound().build()); // 404
    }

    @DeleteMapping("/tutorials/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable int id) {

        return tutorialService.findById(id)
            .flatMap(existing ->
                tutorialService.deleteById(id)
                    .then(Mono.<ResponseEntity<Void>>just(ResponseEntity.noContent().build())) // 204
            )
            .defaultIfEmpty(ResponseEntity.notFound().build()); // 404
    }


}
