package com.example.test_valid.repository;

import com.example.test_valid.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface INewsRepository extends PagingAndSortingRepository<News, Integer> {
    @Query(value = "select * from news where news.news_title like :item or news.category_id like :item",nativeQuery = true)
    Page<News> findAll(Pageable pageable, @Param("item") String item);
}
