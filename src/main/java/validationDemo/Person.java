package validationDemo;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Auther: cyn
 * @Date: 2019-11-04 11:30
 * @Description: the difference between NotEmpty and NotBlank
 * NotEmpty allows " "(space in CharSequence), while NotBlank not.
 *
 *
 */
@Data
public class Person {

    @NotEmpty(message = "name 不能为空")
    String name;

    @NotBlank(message = "address 不能为空")
    String address;

    @NotNull(message = "age 不能为空")
    @Max(value = 20, message = "age 不能大于20")
    Integer age;
    /**
     * 测试发现 NotNull 对boolean不生效，不传时默认为false
     *                  但对Boolean 正常作用;
     *  同理，对基本数据类型，不传的话，为默认值；用封装类正常校验
     */
    @NotNull(message = "isChinese 不能为空")
    Boolean isChinese;
    /**
     * 自定义注解验证枚举类型
     * 枚举传参类型为String
     */
    @NotNull(message = "sex 不能为空")
    @EnumConstraint(message = "sex is not right", target = Sex.class)
    String sex;
}
