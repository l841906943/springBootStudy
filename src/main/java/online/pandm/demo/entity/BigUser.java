package online.pandm.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import online.pandm.demo.annotation.IsNullConvertZero;
import online.pandm.demo.config.JavaBigDecimalJsonSerializer;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author jlcwlp
 * @since 2020-05-02 15:05:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BigUser implements Serializable {

    private static final long serialVersionUID = -97845092797153398L;

    private Integer id;

    private String username;

    private String realName;

    private String password;
    /**
     * 性别：1 女  2 男
     */
    private Integer gender;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;
    /**
     * 1管理员  2经理  3普通用户
     */
    private Integer userType;

    @JsonSerialize(using = JavaBigDecimalJsonSerializer.class)
    //@IsNullConvertZero
    private BigDecimal money;

}