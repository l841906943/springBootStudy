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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: JavaDateJsonSerializer
 * @Description: TODO
 * @Author: Lenovo
 * @Date: 2020/9/14
 * @Version: V1.0
 **/
@Component
public class JavaDateJsonSerializer extends JsonSerializer<Date> implements ContextualSerializer {

    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public JavaDateJsonSerializer() {
    }

    @Override
    public void serialize(Date arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException, JsonProcessingException {
        if (null != arg0) {
            arg1.writeString(this.df.format(arg0));
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        AnnotationMap annotated = property.getMember().getAllAnnotations();

        JsonFormat jsonFormat = annotated.get(JsonFormat.class);
        if (jsonFormat != null && jsonFormat.pattern() != null){
            df = new SimpleDateFormat(jsonFormat.pattern());
        }

        return this;
    }
}