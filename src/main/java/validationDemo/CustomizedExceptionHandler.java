package validationDemo;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

/**
 * @Auther: cyn
 * @Date: 2019-11-04 11:13
 * @Description: 对controller中抛出的特定异常进行处理，e.g. 抛出ValidationException，返回bad request并能知道异常信息
 */
@RestControllerAdvice
public class CustomizedExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handle(ValidationException exception) {
        if (exception instanceof ConstraintViolationException) {
            ConstraintViolationException cve = (ConstraintViolationException) exception;
            Set<ConstraintViolation<?>> constraintViolations = cve.getConstraintViolations();
            for (ConstraintViolation<?> item : constraintViolations) {
                System.out.println(item.getMessage());
            }
        } else {
            System.out.println(exception.getLocalizedMessage());
        }
        return exception.getMessage();
    }


}
