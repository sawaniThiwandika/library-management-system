package lk.ijse.libraryManagementSystem.bo.custom;

import lk.ijse.libraryManagementSystem.bo.SuperBo;
import lk.ijse.libraryManagementSystem.dto.BookDto;
import lk.ijse.libraryManagementSystem.dto.BranchDto;

import java.util.List;

public interface BookBo extends SuperBo {
    public String generateNewBookId();
    public boolean saveBook(BookDto bookDto);
    public List<BookDto> loadAllBook();
    public boolean updateBook(BookDto bookDto);
    BookDto getBook(String id);
    boolean deleteBook(BookDto dto);
}

