package online.pandm.demo.annotation.testTwo;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) //指定作用于字段上
@Retention(RetentionPolicy.RUNTIME) // 项目运行时可以被发现
@Constraint(validatedBy = DataCheckRule.class) // 指定验证规则类
public @interface DataCheck {

    String pattern() default "yyyy-MM-DD";
    String message() default "字段校验通过！";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
