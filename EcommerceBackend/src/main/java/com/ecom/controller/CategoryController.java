package com.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.entity.Category;
import com.ecom.service.CategoryService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/admin")
public class CategoryController {

	@Autowired
	private CategoryService service;
	
}
