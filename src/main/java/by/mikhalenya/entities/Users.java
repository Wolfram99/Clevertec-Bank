package by.mikhalenya.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    private int idUser;
    private String name;
    private String Patronymic;
    private String lastname;
    private String address;
    private String phone;
    private String email;

}
