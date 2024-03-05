package lk.ijse.libraryManagementSystem.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Branch {
    @Id
    String id;
    String name;
    String address;
    String contact;
    String email;
    @OneToMany(mappedBy = "branch",cascade = CascadeType.ALL)
    private List<User> users;
    @OneToMany(mappedBy = "branch",cascade = CascadeType.ALL)
    private List<Book> books;

}
