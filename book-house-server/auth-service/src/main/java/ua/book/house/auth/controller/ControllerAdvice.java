package ua.book.house.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ua.book.house.auth.domain.dto.ErrorMessageDto;
import ua.book.house.auth.exception.UserNotFoundException;

import java.util.Date;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessageDto resourceNotFoundException(UserNotFoundException ex) {
        return ErrorMessageDto.builder()
                              .statusCode(HttpStatus.NOT_FOUND.value())
                              .timestamp(new Date())
                              .message(ex.getMessage())
                              .build();
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessageDto resourceNotFoundException(Exception ex) {
        return ErrorMessageDto.builder()
                              .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                              .timestamp(new Date())
                              .message(ex.getMessage())
                              .build();
    }
}
