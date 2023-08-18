package com.example.test_valid.dto;

import com.example.test_valid.model.Category;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotEmpty;

public class NewsDto implements Validator {
    private int newsId;
    private String newsTitle;
    private String newsContent;
    @NotEmpty(message = "This field is not allowed to be empty!")
    private String date;

    @NotEmpty(message = "This field is not allowed to be empty!")
    private String interview;

    private Category category;

    public NewsDto() {
    }

    public NewsDto(int newsId, String newsTitle, String newsContent, String date, String interview, Category category) {
        this.newsId = newsId;
        this.newsTitle = newsTitle;
        this.newsContent = newsContent;
        this.date = date;
        this.interview = interview;
        this.category = category;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInterview() {
        return interview;
    }

    public void setInterview(String interview) {
        this.interview = interview;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        NewsDto newsDto = (NewsDto) target;
        //Article
        if (newsDto.getNewsTitle().isEmpty()) {
            errors.rejectValue("newsTitle", null, "This field is not allowed to be empty!");
        } else if (newsDto.getNewsTitle().length() > 50){
            errors.rejectValue("newsTitle",null,"Article length should not have more than 50 characters!");
        }
        //Content
        if (newsDto.getNewsContent().isEmpty()) {
            errors.rejectValue("newsContent", null, "This field is not allowed to be empty!");
        } else if (newsDto.getNewsContent().length() > 500) {
            errors.rejectValue("newsContent", null, "Content can contain less than 500 characters!");
        }
    }
}
