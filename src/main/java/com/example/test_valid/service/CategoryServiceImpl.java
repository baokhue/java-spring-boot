package com.example.test_valid.service;

import com.example.test_valid.model.Category;
import com.example.test_valid.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;


    @Override
    public List<Category> getInformation() {
        return categoryRepository.findAll();
    }
}
