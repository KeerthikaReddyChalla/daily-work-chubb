package com.app.quizzApp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.quizzApp.model.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
	List<Question> findByCategory(String category);

	@Query(value = "SELECT * FROM question q WHERE q.category = ?1 ORDER BY RAND()", nativeQuery = true)
	List<Question> findRandomQuestionsByCategory(String category);

}
