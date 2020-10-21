package online.pandm.demo.util;

import lombok.extern.slf4j.Slf4j;
import online.pandm.demo.annotation.IsNullConvertZero;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @ClassName: IsNullConvertZeroUtil
 * @Description: TODO
 * @Author: Lenovo
 * @Date: 2020/9/15
 * @Version: V1.0
 **/
@Slf4j
public class IsNullConvertZeroUtil {

    public static Object checkIsNull(Object obj) {
        try {
            Class<?> clazz = obj.getClass();
            //获得私有的成员属性
            Field[] fields = clazz.getDeclaredFields();
            if(Objects.nonNull(fields) && fields.length>0){
                for(Field field : fields){
                    field.setAccessible(true);
                    //判断IsNullConvertZero注解是否存在
                    if(field.isAnnotationPresent(IsNullConvertZero.class)){
                        if(Objects.isNull(field.get(obj))){
                            field.set(obj, BigDecimal.ZERO);
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("IsNullConvertZeroUtil出现异常：{}",e);
        }
        return obj;
    }

    /**
     * 获取所有字段为null的属性名
     * 用于BeanUtils.copyProperties()拷贝属性时，忽略空值
     * @param source
     * @return
     */
    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}

