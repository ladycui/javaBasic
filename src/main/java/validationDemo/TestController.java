package validationDemo;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.*;
import java.util.List;

/**
 * @Auther: cyn
 * @Date: 2019-11-04 10:23
 * @Description: an implementation of Validation( reference: https://www.cnblogs.com/mooba/p/11276062.html)
 * review points：
 * 1. 单个参数校验(controller上添加@Validated）；
 * 2. bean校验；
 * 3. 处理异常（处理类exceptionHandler有@ControllerAdvice）；
 * 4. 配置validator，failFast机制
 */
@RestController
@Slf4j
@Validated
public class TestController {

    /**
     * 1. 单个参数校验，要在类上加上Spring的@Validated注解；
     *      若bean的话，只需在bean前@Valid or @Validated即可。
     * 2. 若有异常，会抛出ConstraintViolationException；
     *      统一异常捕获，使用@ControllerAdvice（@RestControllerAdvice）注解的类处理异常，详见ExceptionHandler类
     * 3. 默认的校验注解（@NotNull等）会抛出异常，exceptionHandler可以捕获异常并返回定制化异常信息，而不是返回默认的异常信息；
     *    注：@Valid或@Validation不会抛出异常。
     * 4. 递归验证，即验证对象中对象属性，e.g. 验证User中Person属性，在Person中添加@Valid，参加6.5th函数；
     *    若验证List，参见7th函数
     */
    @GetMapping("/test/get")
    public String testGet(@NotNull String name) {
        log.info("testGet name: {}", name);
        return name;
    }


    @GetMapping("/test/get2")
    public Result testGet(@NotBlank(message = "name is blank") @Pattern(regexp = "[\\w.]+", message = "name format is wrong") String name,// \w = [_a-zA-Z0-9]
                          @NotNull @Min(value = 100) Integer age) {
        log.info("testGet name: {}, age: {}", name, age);
        return new Result();
    }


    @GetMapping("/test/get4")
    public String testGet4(@NotEmpty String name) {
        log.info("testGet4 name: {}", name);
        return name;
    }

    /**
     * 此时返回数据格式是默认格式（很长很难看），推荐使用下面6的定制化返回异常信息。
     */
    @PostMapping("/test/get5")
    public String testGet5(@RequestBody @Valid Person input) {
        log.info("/test/get5, input: {}", JSONObject.toJSONString(input));
        return JSONObject.toJSONString(input);
    }

    /**
     * 参数校验结果会被存到BindingResult中
     * 两种校验模式：1. 普通模式： 校验完所有属性，并返回所有验证失败的信息
     * 2. 快速模式：只要有一个校验失败，则返回
     * 普通模式（默认），快速模式详见ValidatorConfig类（配置了ValidatorFactory，设置其failFast为true）
     *
     * @param input
     * @param bindingResult
     * @return
     */
    @PostMapping("/test/get6")
    public String testGet6(@RequestBody @Validated Person input, BindingResult bindingResult) {//此处@Valid和@validated无差别
        log.info("/test/get6, input: {}", JSONObject.toJSONString(input));
        validateInput(bindingResult);
        return JSONObject.toJSONString(input);
    }

    /**
     * 递归验证对象中对象属性，在对象中对象前添加@Valid
     * @param user
     * @param bindingResult
     * @return
     */
    @PostMapping("/test/recursive2")
    public String testGet6_5(@RequestBody @Valid User2 user, BindingResult bindingResult) {
        log.info("/test/recursive, input: {}", JSONObject.toJSONString(user));
        validateInput(bindingResult);
        return JSONObject.toJSONString(user);
    }

    /**
     * 递归验证 对象中对象的属性， 例：User中Person对象里的属性。
     * @param user
     * @param bindingResult
     * @return
     */
    @PostMapping("/test/recursive")
    public String testGet7(@RequestBody @Valid User user, BindingResult bindingResult) {
        log.info("/test/recursive, input: {}", JSONObject.toJSONString(user));
        validateInput(bindingResult);
        return JSONObject.toJSONString(user);
    }



    private void validateInput(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuffer sb = new StringBuffer();//StringBuffer vs StringBuilder
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError error : allErrors) {
                sb.append(error.getDefaultMessage());
            }
            throw new ValidationException(sb.toString());
        }
    }

}
