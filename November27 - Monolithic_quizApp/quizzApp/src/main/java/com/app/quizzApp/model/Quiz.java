package com.app.quizzApp.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Quiz {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String title;
	
	@ManyToMany
	private List<Question> questions;
}
