package com.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Question {

	@Id
	private Integer id;
	private String answer;
	private String question;
	private Integer value;
	private LocalDateTime airdate;
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
	private Integer game_id;
	private Integer invalid_count;

	@OneToOne
	private Category category;
}
