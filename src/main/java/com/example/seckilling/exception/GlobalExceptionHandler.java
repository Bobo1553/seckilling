package com.example.seckilling.exception;

import com.example.seckilling.result.CodeMsg;
import com.example.seckilling.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Xiao Yijia
 * @create 2020/9/17 9:45
 */

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalException.class);

    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request, Exception exception) {
        if (exception instanceof GlobalException) {
            GlobalException globalException = (GlobalException) exception;
            return Result.error(globalException.getCodeMsg());
        } else if (exception instanceof BindException) {
            BindException bindException = (BindException) exception;
            List<ObjectError> errors = bindException.getAllErrors();
            ObjectError error = errors.get(0);
            String msg = error.getDefaultMessage();
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(msg));
        } else {
            LOG.info(exception.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

}
