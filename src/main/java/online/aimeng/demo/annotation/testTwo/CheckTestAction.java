package online.aimeng.demo.annotation.testTwo;

public class CheckTestAction {

    public static void main(String[] args) {
        User user = new User();
        user.setName("卢朋");
        user.setSex("男");
        user.setBirth("1995-0506");
        System.out.println("成功了吗！！！");
        System.out.println(user.toString());
    }
    
}
