package online.pandm.demo.config;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.AnnotationMap;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @ClassName: JavaBigDecimalJsonSerializer
 * @Description: TODO
 * @Author: Lenovo
 * @Date: 2020/9/14
 * @Version: V1.0
 **/
public class JavaBigDecimalJsonSerializer extends JsonSerializer<BigDecimal> {
    public JavaBigDecimalJsonSerializer() {
    }
    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        if (value != null) {
            gen.writeNumber(value.setScale(2, BigDecimal.ROUND_HALF_DOWN));
        } else {
            gen.writeNumber(BigDecimal.ZERO);
        }
    }
}