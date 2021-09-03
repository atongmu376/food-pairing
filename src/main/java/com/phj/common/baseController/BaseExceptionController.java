package com.phj.common.baseController;

import com.phj.pojo.Result;
import com.phj.pojo.StatusCode;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * @program: food-pairing
 * @description:
 * @author: Mr.Pan
 * @create: 2021-08-22 12:52
 **/

@RestControllerAdvice
public class BaseExceptionController {
    private final Logger log = LoggerFactory.getLogger(BaseExceptionController.class);

    //处理@Validated注解异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<MethodArgumentNotValidException> validationBodyException(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        String message = "";
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            if (errors != null) {
                errors.forEach(p -> {
                    FieldError fieldError = (FieldError) p;
                    log.error("Data check failure : object{" + fieldError.getObjectName() + "},field{" + fieldError.getField() +
                            "},errorMessage{" + fieldError.getDefaultMessage() + "}");

                });
                if (errors.size() > 0) {
                    FieldError fieldError = (FieldError) errors.get(0);
                    message = fieldError.getDefaultMessage();
                }
            }
        }
        return new Result(false, StatusCode.ERROR,"".equals(message) ? "请填写正确信息" : message);
    }



    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Result<Exception> exceptionResult(SQLIntegrityConstraintViolationException e){

        return new Result<>(false, StatusCode.ERROR, e.getMessage());
    }

//    @ExceptionHandler(Exception.class)
//    public Result<Exception> exceptionResult(Exception e){
//
//        return new Result<>(false, StatusCode.ERROR, e.getMessage());
//    }

    @ExceptionHandler(value = AuthorizationException.class)
    public Result defaultErrorHandler(){
        return new Result<AuthorizationException>(false, StatusCode.ACCESSERROR, "权限不足");
    }
}
