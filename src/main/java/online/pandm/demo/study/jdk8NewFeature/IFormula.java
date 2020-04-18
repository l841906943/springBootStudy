package online.pandm.demo.study.jdk8NewFeature;

public interface IFormula {

    double calculate(int a);

    /*
        求平方
     */
    default double sqrt(int a) {
        return Math.sqrt(a);
    }


}
