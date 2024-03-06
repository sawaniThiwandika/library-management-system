package lk.ijse.libraryManagementSystem.dto;

import lk.ijse.libraryManagementSystem.entity.Branch;
import lk.ijse.libraryManagementSystem.entity.UserBookDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    String name;
    String email;
    Branch branch;
    String password;
    private List<UserBookDetails> transactions;
    String contact;
}
