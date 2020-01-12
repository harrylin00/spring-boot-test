package com.example.demo.controller;

import com.example.demo.dto.PaginationDTO;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    String profile(@PathVariable(name = "action") String action,
                   Model model,
                   HttpServletRequest request,
                   @RequestParam(value = "page", defaultValue = "1") Integer page,
                   @RequestParam(value = "size", defaultValue = "2") Integer size) {
        if("questions".equals(action)) {
            model.addAttribute("sectionName", "My Question");
            model.addAttribute("section", "questions");
        } else if("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "My Reply");
        }

        User user = (User) request.getSession().getAttribute("user");
        if(user == null) {
            model.addAttribute("error", "user not log in.");
            return "index";
        }

        PaginationDTO paginationDTO = questionService.getQuestionByUserId(user, page, size);
        model.addAttribute("pagination", paginationDTO);
        return "profile";
    }
}
