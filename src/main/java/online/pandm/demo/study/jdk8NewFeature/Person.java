package online.pandm.demo.study.jdk8NewFeature;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Person {
    public String firstName;
    public String lastName;

    public Person() {}

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
