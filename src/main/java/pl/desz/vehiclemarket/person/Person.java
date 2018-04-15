package pl.desz.vehiclemarket.person;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {
    private String firstName;
    private String lastName;
    private String contactNumber;
}
