package online.pandm.demo.annotation;

import java.lang.annotation.*;

/**
 * @author Lenovo
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IsNullConvertZero {
}