package lk.ijse.libraryManagementSystem.dto.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BranchTm {
    private String id;
    private String address;
    private String contact;
    private  String email;
    private Button update;
    private Button delete;

}
