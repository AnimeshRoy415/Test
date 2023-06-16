package com.models;

import com.dtos.QuestionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CorrectAnswer {

	private String correct_answer;
	private QuestionDTO question;

}
