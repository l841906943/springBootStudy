package online.aimeng.demo.study.jdk8NewFeature;

@FunctionalInterface
public interface IPersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}
