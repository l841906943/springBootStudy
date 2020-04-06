package online.aimeng.demo.study.jdk8NewFeature;

/**
 * 所谓函数式接口(Functional Interface)就是只包含一个抽象方法的声明。针对该接口类型的所有 Lambda 表达式都会与这个抽象方法匹配。
 * {另外，只是在接口上添加default并不算抽象方法}
 *
 * 总结：为了保证一个接口明确的被定义为一个函数式接口(Functional Interface)，我们需要为该接口添加注解：
 * @FunctionalInterface。这样，一旦你添加了第二个抽象方法，编译器会立刻抛出错误提示。
 * @param <F>
 * @param <T>
 */
@FunctionalInterface
public interface IConverter<F, T> {

    T convert(F from);

}
