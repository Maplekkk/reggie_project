package com.maple.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * ClassName:GlobalExceptionHandler
 * Package: com.maple.common
 * Description:
 * Author maple
 * Create 2023-01-11
 * Version: v1.0
 */
// 会返回json数据的用Rest....
@RestControllerAdvice(annotations = {RestController.class, Controller.class})
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException exception){
        log.error(exception.getMessage());

        //Duplicate entry 'maple' for key 'employee.idx_username'
        if(exception.getMessage().contains("Duplicate entry"))
        {
            String[] s = exception.getMessage().split(" ");
            return R.error(s[2] + "已存在");
        }

        return R.error("新增失败, 账号不能重复");
    }

}
