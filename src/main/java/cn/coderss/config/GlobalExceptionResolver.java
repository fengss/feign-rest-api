package cn.coderss.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 全局异常处理
 *
 * @author shenwei
 */
@ControllerAdvice
@Component
@Slf4j
public class GlobalExceptionResolver {
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    @ExceptionHandler(value = {BindException.class, MethodArgumentNotValidException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map handle(Exception exception) {
        log.error("BindException MethodArgumentNotValidException");
        BindingResult bindResult = null;
        if (exception instanceof BindException) {
            bindResult = ((BindException) exception).getBindingResult();
        } else if (exception instanceof MethodArgumentNotValidException) {
            bindResult = ((MethodArgumentNotValidException) exception).getBindingResult();
        }
        return bindResult.getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .map(message->new HashMap<String,String>(){{
                    put("code", "2");
                    put("message", message);
                    put("data", null);
                }})
                .collect(Collectors.toList()).get(0);
    }


    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map handle(ConstraintViolationException exception) {
        log.error("ViolationException");
        return exception.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .map(message->new HashMap<String,String>(){{
                    put("code", "2");
                    put("message", message);
                    put("data", null);
                }})
                .collect(Collectors.toList()).get(0);
    }


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String exceptionHandler(Exception e){
        log.error("服务异常:{}", e.getMessage());
        //对重复性数据进行处理并返回json
        return  "{\"code\":2, \"message\":\"操作失败\"}";
    }
}
