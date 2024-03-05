package lk.ijse.libraryManagementSystem.bo;

import lk.ijse.libraryManagementSystem.dto.BranchDto;

import java.util.List;

public interface BranchBo {
    public String generateNewBranchId();
    public boolean saveBranch(BranchDto branchDto);
    public List<BranchDto> loadAllBranch();
}
