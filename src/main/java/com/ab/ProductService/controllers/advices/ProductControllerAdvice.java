package com.ab.ProductService.controllers.advices;

import com.ab.ProductService.dtos.ExceptionDto;
import com.ab.ProductService.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice

//@ControllerAdvice(assignableTypes = {ProductController.class})
// we can pass an argument as ProductController to act it as advice for specific controller currently it is global
// for all controller on base package, we should always have 1 global advice. If it is global add it as
// @RestControllerAdvice
public class ProductControllerAdvice {
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    private ExceptionDto handleProductNotFoundException(ProductNotFoundException e){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(e.getMessage());
        exceptionDto.setStatus("Failure");
        return exceptionDto;
    }
}
