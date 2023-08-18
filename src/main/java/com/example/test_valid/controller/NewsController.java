package com.example.test_valid.controller;

import com.example.test_valid.dto.NewsDto;
import com.example.test_valid.model.News;
import com.example.test_valid.service.INewsService;
import com.example.test_valid.service.ICategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private INewsService newsService;

    @GetMapping("")
    public String getInformationCustomer(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "")String searchItem,
                                         ModelMap model){
        Pageable pageable = PageRequest.of(page,5);
        Page<News> newsPages = newsService.findAll(pageable, searchItem);

        model.addAttribute("newsPages", newsPages);
        model.addAttribute("searchItem", searchItem);
        model.addAttribute("news", new News());
        model.addAttribute("newsDto", new NewsDto());
        return "/home";
    }

    @GetMapping("/create")
    public String getFormCreate( ModelMap model){
        model.addAttribute("newsDto", new NewsDto());
        model.addAttribute("categoryService", categoryService.getInformation());
        return "/create";
    }

//    @GetMapping("/update/{id}")
//    public String getFormUpdate(@PathVariable int id, ModelMap model){
//        model.addAttribute("project",newsService.findById(id));
//        model.addAttribute("categoryService", categoryService.getInformation());
//        return "/update";
//    }

    /*@PostMapping("/update")
    public String updateCustomer(@Valid @ModelAttribute ProjectDto projectDto, BindingResult bindingResult, RedirectAttributes redirectAttributes){
//        new ProjectDto().validate(projectDto, bindingResult);
//        if (bindingResult.hasErrors()){
//            return "/create";
//        }

        Project project = new Project();
        BeanUtils.copyProperties(projectDto, project);
        projectService.add(project);
        redirectAttributes.addFlashAttribute("mess", "Successful create!");
        redirectAttributes.addFlashAttribute("status", "success");
        return "redirect:/project";
    }*/
//    @PostMapping("/update")
//    public String updateProject(@ModelAttribute News project, RedirectAttributes redirectAttributes){
//        /*Project project = new Project();
//        BeanUtils.copyProperties(projectDto, project);*/
//        projectService.update(project);
//        redirectAttributes.addFlashAttribute("mess", "Successful update!");
//        redirectAttributes.addFlashAttribute("status", "success");
//        return "redirect:/project";
//    }

    @PostMapping("/delete")
    public String deleteProject(@ModelAttribute News news, RedirectAttributes redirectAttributes){
        newsService.delete(news);
        redirectAttributes.addFlashAttribute("mess", "Successful delete!");
        redirectAttributes.addFlashAttribute("status", "success");
        return "redirect:/news";
    }

    @PostMapping("/create")
    public String createCustomer(@Valid @ModelAttribute NewsDto newsDto, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        new NewsDto().validate(newsDto, bindingResult);
        if (bindingResult.hasErrors()){
            return "/create";
        }

        News news = new News();
        BeanUtils.copyProperties(newsDto, news);
        newsService.add(news);
        redirectAttributes.addFlashAttribute("mess", "Successful create!");
        redirectAttributes.addFlashAttribute("status", "success");
        return "redirect:/news";
    }

    @GetMapping("/detail/{id}")
    public String showDetail(@PathVariable int id, Model model){
        model.addAttribute("news",newsService.findById(id));
        return "/detail";
    }


}
