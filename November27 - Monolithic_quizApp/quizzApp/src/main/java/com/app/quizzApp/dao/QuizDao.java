package com.app.quizzApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.quizzApp.model.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer>{
	


}
