package homeworkstream.model;

import homeworkstream.enums.Gender;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Person {
    private String name;
    private int age;
    private double weight;
    private Gender gender;
    private List<Phone> phones;


}
