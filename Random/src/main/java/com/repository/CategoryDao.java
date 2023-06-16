package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.models.Category;

public interface CategoryDao extends JpaRepository<Category, Integer>{

}
