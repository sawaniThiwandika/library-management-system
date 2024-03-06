package lk.ijse.libraryManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AdminDto {
    String email;
    String password;
    String name;
    String address;
    String contact;
}
