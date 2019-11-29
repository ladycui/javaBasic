package validationDemo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Auther: cyn
 * @Date: 2019-11-28 16:41
 * @Description:
 */
public class EnumValidator implements ConstraintValidator<EnumConstraint, String> {
    Class<?>[] cls;//枚举类

    @Override
    public void initialize(EnumConstraint constraintAnnotation) {
        cls = constraintAnnotation.target();
    }

    @Override
    public boolean isValid(String sexEnum, ConstraintValidatorContext constraintValidatorContext) {
        if (cls.length > 0) {
            for (Class<?> cl : cls) {
                try {
                    if (cl.isEnum()) {
                        Object[] objs = cl.getEnumConstants();
                        Method method = cl.getMethod("name");
                        for (Object obj : objs) {
                            Object code = method.invoke(obj, null);
                            if (sexEnum.equals(code.toString())) {
                                return true;
                            }
                        }
                    }
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

}
