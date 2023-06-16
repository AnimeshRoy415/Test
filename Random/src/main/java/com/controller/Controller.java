package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.models.CorrectAnswer;
import com.dtos.QuestionAnswerDTO;
import com.dtos.QuestionDTO;
import com.service.Service;

@RestController
@RequestMapping("api")
public class Controller {

	@Autowired
	private Service service;

	@GetMapping("/random")
	public ResponseEntity<String> saveData() {

		return new ResponseEntity<>(service.fetchAPI(), HttpStatus.ACCEPTED);
	}

	@GetMapping("/play")
	public ResponseEntity<QuestionDTO> findQuestion() {

		return new ResponseEntity<>(service.randomQuestion(), HttpStatus.OK);
	}

	@PostMapping("/next")
	public ResponseEntity<CorrectAnswer> postAnswerGetNewQuestion(@RequestBody QuestionAnswerDTO questionAnswerDTO) {

		CorrectAnswer correctAnswer= service.getCorrectAnswerAndNextQuestion(questionAnswerDTO);
		return new ResponseEntity<>(correctAnswer, HttpStatus.OK);
	}
}
