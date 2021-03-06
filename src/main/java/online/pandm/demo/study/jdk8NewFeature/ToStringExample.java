package online.pandm.demo.study.jdk8NewFeature;

import lombok.ToString;

@ToString(exclude="id")
public class ToStringExample {

    private static final int STATIC_VAR = 10;
    private String name;
    private Shape shape = new Square(5, 10);
    private String[] tags;
    private int id;
    public String getName() {
        return this.name;
    }

    @ToString(includeFieldNames=true)
    public static class Square extends Shape {
        private final int width, height;
        public Square(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }
}