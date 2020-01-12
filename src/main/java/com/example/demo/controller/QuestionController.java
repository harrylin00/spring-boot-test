package com.example.demo.controller;

import com.example.demo.dto.QuestionDTO;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/question/{id}")
    String question(@PathVariable("id") Integer questionId,
                    Model model) {
        QuestionDTO questionDTO = questionService.getQuestionByQuestionId(questionId);
        questionService.increaseView(questionId);
        model.addAttribute("question", questionDTO);
        return "question";
    }
}
