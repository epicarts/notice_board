package com.osstem.notice.controller.common;

import com.osstem.notice.controller.common.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * IllegalArgumentException
     * 적합하지 않거나(illegal) 적절하지 못한(inappropriate) 인자를 넘겨줌
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class})
    public ErrorResult illegalExHandler(IllegalArgumentException e) {
        return ErrorResult.builder()
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .errorMessage(e.getMessage())
                .build();
    }

    /**
     * MethodArgumentNotValidException
     * @Valid 으로 검증실패시 binding error 시에 발생
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ErrorResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();

        StringBuilder sb = new StringBuilder();
        for (FieldError f : bindingResult.getFieldErrors()) {
            sb
                    .append("[")
                    .append(f.getField())
                    .append(": ")
                    .append(f.getDefaultMessage())
                    .append("]");
        }

        return ErrorResult.builder()
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .errorMessage(sb.toString())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ErrorResult illegalExHandler(HttpMessageNotReadableException e) {
        return ErrorResult.builder()
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .errorMessage("HttpMessageNotReadableException")
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorResult entityNotFoundExHandler(EntityNotFoundException e) {
        return ErrorResult.builder()
                .errorCode(HttpStatus.NOT_FOUND.value())
                .errorMessage(e.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(IOException.class)
    public ErrorResult IOExHandler(IOException e) {
        return ErrorResult.builder()
                .errorCode(HttpStatus.BAD_GATEWAY.value())
                .errorMessage(e.getMessage())
                .build();
    }
}
