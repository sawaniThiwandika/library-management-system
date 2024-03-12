package lk.ijse.libraryManagementSystem.bo;

import lk.ijse.libraryManagementSystem.dto.BookDto;
import lk.ijse.libraryManagementSystem.dto.BranchDto;

import java.util.List;

public interface BookBo {
    public String generateNewBookId();
    public boolean saveBook(BookDto bookDto);
    public List<BookDto> loadAllBook();
    public boolean updateBook(BookDto bookDto);
    BookDto getBook(String id);
    boolean deleteBook(BookDto dto);
}

