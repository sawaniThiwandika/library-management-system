package lk.ijse.libraryManagementSystem.bo.impl;

import lk.ijse.libraryManagementSystem.bo.BookBo;
import lk.ijse.libraryManagementSystem.dto.BookDto;
import lk.ijse.libraryManagementSystem.dto.BranchDto;

import java.util.List;

public class BookBoImpl implements BookBo {
    @Override
    public String generateNewBookId() {
        return null;
    }

    @Override
    public boolean saveBook(BranchDto branchDto) {
        return false;
    }

    @Override
    public List<BookDto> loadAllBook() {
        return null;
    }
}
