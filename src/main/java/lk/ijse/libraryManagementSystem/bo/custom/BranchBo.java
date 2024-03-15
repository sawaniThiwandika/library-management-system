package lk.ijse.libraryManagementSystem.bo.custom;

import lk.ijse.libraryManagementSystem.bo.SuperBo;
import lk.ijse.libraryManagementSystem.dto.BranchDto;

import java.util.List;

public interface BranchBo extends SuperBo {
    public String generateNewBranchId();
    public boolean saveBranch(BranchDto branchDto);
    public List<BranchDto> loadAllBranch();
    public boolean updateBranch(BranchDto branchDto);

    boolean deleteBranch(BranchDto id);
   BranchDto getBranch(String id);


}
