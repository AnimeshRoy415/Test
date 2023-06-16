package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.models.Question;
import org.springframework.data.jpa.repository.Query;

public interface QuestionDao extends JpaRepository<Question, Integer> {

}
