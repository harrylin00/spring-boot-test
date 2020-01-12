package com.example.demo.advice;

import com.example.demo.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e,
                        Model model) {
        if(e instanceof CustomizeException)
            model.addAttribute("message", e.getMessage());
        else
            model.addAttribute("message", "server done.");
        return new ModelAndView("error");
    }
}
