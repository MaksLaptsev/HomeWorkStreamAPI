package homeworkstream.model;

import homeworkstream.enums.Operator;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Phone {
    private String number;
    private Operator operator;
}
