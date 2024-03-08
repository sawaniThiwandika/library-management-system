package lk.ijse.libraryManagementSystem.bo;

import lk.ijse.libraryManagementSystem.dto.BookDto;
import lk.ijse.libraryManagementSystem.dto.BranchDto;
import lk.ijse.libraryManagementSystem.entity.Branch;

import java.util.List;

public interface BranchBo {
    public String generateNewBranchId();
    public boolean saveBranch(BranchDto branchDto);
    public List<BranchDto> loadAllBranch();
    public boolean updateBranch(BranchDto branchDto);
}
