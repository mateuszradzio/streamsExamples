import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
@FieldDefaults(level=AccessLevel.PRIVATE)
public class Employee {

    String firstName;
    String lastName;
    int age;
    List<String> skills;
}
