package com.service;


import com.dtos.QuestionAnswerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.models.Category;
import com.models.CorrectAnswer;
import com.models.Question;
import com.dtos.QuestionDTO;
import com.repository.CategoryDao;
import com.repository.QuestionDao;

import java.util.*;

@Component
public class Service {

	@Autowired
	private RestTemplate restTemplate;

	private static final String url = "https://jservice.io/api/random";

	@Autowired
	private QuestionDao questionDao;

	@Autowired
	private CategoryDao categoryDao;

	int count = 0;
	public String fetchAPI() {

		ResponseEntity<Question[]> response = restTemplate.getForEntity(url, Question[].class);
		Question[] body = response.getBody();
		Category category = body[0].getCategory();
		List<Question> getOptQuestion= questionDao.findAll();
		if (getOptQuestion !=null && getOptQuestion.size() >=5){
			return "DataBase Is Full Please Remove Some Data";
		} else {
			categoryDao.save(category);
			questionDao.save(body[0]);
		}
		return "Data saved successfully";
	}

	public QuestionDTO randomQuestion() {

		List<Question> questionList= questionDao.findAll();
		Random random = new Random();
		int length= questionList.size()-1;

		if(count<=length){
			Question question= questionList.get(count++);
			return new QuestionDTO(question.getId(), question.getQuestion());
		}
		else{
			count=0;
			Question question= questionList.get(count++);
			return new QuestionDTO(question.getId(), question.getQuestion());
		}
	}


		public CorrectAnswer getCorrectAnswerAndNextQuestion(QuestionAnswerDTO questionAnswerDTO){

			int questionId= questionAnswerDTO.getQuestion_id();
			return this.findQuestionById(questionId);
		}

		public CorrectAnswer findQuestionById(Integer questionId) {

			CorrectAnswer correctAnswer= new CorrectAnswer();
			Optional<Question> getOptQuestion= questionDao.findById(questionId);

			if (getOptQuestion !=null){
				Question getQuestion= getOptQuestion.get();
				correctAnswer.setCorrect_answer(getQuestion.getAnswer());
			}
			
			QuestionDTO getrandQuestion = this.randomQuestion();
			correctAnswer.setQuestion(getrandQuestion);
			return correctAnswer;

		}
}
