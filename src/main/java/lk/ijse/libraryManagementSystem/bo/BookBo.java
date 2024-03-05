package lk.ijse.libraryManagementSystem.bo;

import lk.ijse.libraryManagementSystem.dto.BookDto;
import lk.ijse.libraryManagementSystem.dto.BranchDto;

import java.util.List;

public interface BookBo {
    public String generateNewBookId();
    public boolean saveBook(BranchDto branchDto);
    public List<BookDto> loadAllBook();
}
