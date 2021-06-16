package com.demosocket.manager.controller;

import com.demosocket.manager.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler({EntityNotFoundException.class, UserNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String exception(final Exception ex, final Model model) {
        String errorMessage = ex != null ? ex.getMessage() : "Unknown error";
        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }
}
