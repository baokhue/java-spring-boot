package com.example.test_valid.service;

import com.example.test_valid.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface INewsService {
    boolean add(News news);
    void delete(News news);
    News findById(int id);
    Page<News> findAll(Pageable pageable, String item);
}
