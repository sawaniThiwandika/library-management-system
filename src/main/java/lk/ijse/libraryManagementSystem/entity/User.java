package lk.ijse.libraryManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {
    String name;
    @Id
    String email;
    @ManyToOne
    Branch branch;
    @JoinColumn (name="branch_id")
    String password;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Transaction> transactions;
    String contact;



}
