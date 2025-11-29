package com.app.quizzApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.quizzApp.dao.QuestionDao;
import com.app.quizzApp.model.Question;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        List<Question> list = questionDao.findByCategory(category);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question);
        return new ResponseEntity<>("Question Added Successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<String> updateQuestion(Integer id, Question updatedQuestion) {
        Optional<Question> existing = questionDao.findById(id);
        if (existing.isPresent()) {
            updatedQuestion.setId(id);
            questionDao.save(updatedQuestion);
            return new ResponseEntity<>("Question Updated Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Question Not Found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> deleteQuestion(Integer id) {
        Optional<Question> existing = questionDao.findById(id);
        if (existing.isPresent()) {
            questionDao.deleteById(id);
            return new ResponseEntity<>("Question Deleted Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Question Not Found", HttpStatus.NOT_FOUND);
    }
}
