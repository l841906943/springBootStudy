package online.aimeng.demo.study.jdk8NewFeature;

/**
 * 访问成员变量和静态变量
 * Lambda 表达式中对成员变量和静态变量拥有读写权限
 */
public class Lambda4 {
    // 静态变量
    static int outerStaticNum;
    // 成员变量
    int outerNum;
    void testScopes() {
        IConverter<Integer, String> stringConverter1 = (from) -> {
            // 对成员变量赋值
            System.out.println("111111111111");
            outerNum = 23;
            return String.valueOf(from);
        };
        stringConverter1.convert(11);
        IConverter<Integer, String> stringConverter2 = (from) -> {
            // 对静态变量赋值
            System.out.println("111111111111");
            outerStaticNum = 72;
            return String.valueOf(from);
        };
        stringConverter2.convert(11);
    }

    public static void main(String[] args) {
        Lambda4 testLam = new Lambda4();
        testLam.testScopes();
        System.out.println(outerStaticNum+"-----------"+testLam.outerNum);
    }
}
