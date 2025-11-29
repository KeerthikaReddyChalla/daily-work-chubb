package com.app.quizzApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.quizzApp.service.QuizService;
import com.app.quizzApp.model.QuestionWrapper;
import com.app.quizzApp.model.Response;

@RestController
@RequestMapping("quiz")
public class QuizController {
	@Autowired
	QuizService quizService;
	
	@PostMapping("create")
	public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
		return quizService.createQuiz(category, numQ, title);
		
	}
	
	@GetMapping("get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
		return quizService.getQuizQuestions(id);
		
	}
	
	@PostMapping("submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
		return quizService.calculateResult(id, responses);
		
	}
	

}
