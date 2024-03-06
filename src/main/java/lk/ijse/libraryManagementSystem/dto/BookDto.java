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
public class BookDto {
    String id;
    private Branch branch;
    private List<UserBookDetails> transactions;
    String title;
    String author;
    String genre;
    String imagePath;
    boolean isAvailable;
}
