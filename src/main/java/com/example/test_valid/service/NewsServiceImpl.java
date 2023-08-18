package com.example.test_valid.service;

import com.example.test_valid.model.News;
import com.example.test_valid.repository.INewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements INewsService {

    @Autowired
    private INewsRepository newsRepository;

    @Override
    public boolean add(News news) {
        News news1 = newsRepository.save(news);
        return news1!= null;
    }

    @Override
    public void delete(News news) {
        newsRepository.deleteById(news.getNewsId());
    }

    @Override
    public News findById(int id) {
        return newsRepository.findById(id).orElse(null);
    }

    @Override
    public Page<News> findAll(Pageable pageable, String item) {
        return newsRepository.findAll(pageable, "%" + item + "%");
    }
}
