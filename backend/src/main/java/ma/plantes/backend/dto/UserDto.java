package ma.plantes.backend.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserDto {
    private String username;


    private String password;


    private String firstName;

    private String lastName;

    private String phoneNumber;
}
