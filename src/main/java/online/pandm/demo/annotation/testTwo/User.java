package online.pandm.demo.annotation.testTwo;

import lombok.Data;

/**
 * @author Lenovo
 */
@Data
public class User {

    private String name;
    private String sex;
    @DataCheck(message = "birth合适有误11111111")
    private String birth;

}
