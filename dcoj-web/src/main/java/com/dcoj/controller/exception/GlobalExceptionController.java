package com.dcoj.controller.exception;

import com.dcoj.entity.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Leon
 **/
@RestControllerAdvice
public class GlobalExceptionController {

//    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    /**
     * 方法参数无效异常
     * @param e     被捕捉到的MethodArgumentNotValidException
     * @return      一个新的ResponseEntity对象，包含内容：HttpStatus.BAD_REQUEST.value()， ERROR_MESSAGE
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity handle(MethodArgumentNotValidException e) {
        FieldError error = e.getBindingResult().getFieldError();
        return new ResponseEntity(HttpStatus.BAD_REQUEST.value(), error.getField()+": "+error.getDefaultMessage(), null);
    }


    /**
     *  邮件验证异常
     * @param e EmailVerifyException
     * @return  一个新的ResponseEntity对象，包含内容：HttpStatus.BAD_REQUEST.value()， 验证异常错误信息
     */
    @ExceptionHandler(EmailVerifyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity handle(EmailVerifyException e) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
    }
/*
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity handle(ConstraintViolationException e) {
        String s = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList()).get(0);
        return new ResponseEntity(HttpStatus.BAD_REQUEST.value(), s, null);
    }

    @ExceptionHandler(ShiroException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity handleShiro(HttpServletRequest request, Throwable ex) {
        LOGGER.info(ex.getMessage());
        return new ResponseEntity(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), null);
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity handleForbidden(HttpServletRequest request, Throwable ex) {
        LOGGER.info(ex.getMessage());
        return new ResponseEntity(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(), null);
    }*/

    @ExceptionHandler(WebErrorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity handleErrorException(HttpServletRequest request, Throwable ex) {
//        LOGGER.info(ex.getMessage());
        return new ResponseEntity(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null);
    }

/*    @ExceptionHandler(ServletRequestBindingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity handleBindErrorException(HttpServletRequest request, Throwable ex) {
        LOGGER.info(ex.getMessage());
        return new ResponseEntity(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity globalException(HttpServletRequest request, Throwable ex) {
        LOGGER.error(ex.getMessage(), ex);
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null);
    }*/

    /*private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }*/
}
