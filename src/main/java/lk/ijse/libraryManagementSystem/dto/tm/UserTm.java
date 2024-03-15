package lk.ijse.libraryManagementSystem.dto.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class UserTm {
    private int number;
    private String name;
    private String email;
    private String contact;

    Button viewHistory;
    Button update;
    Button delete;



}
