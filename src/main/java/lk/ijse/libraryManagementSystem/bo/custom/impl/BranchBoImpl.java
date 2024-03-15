package lk.ijse.libraryManagementSystem.bo.custom.impl;

import lk.ijse.libraryManagementSystem.bo.custom.BranchBo;
import lk.ijse.libraryManagementSystem.dao.custom.BranchDao;
import lk.ijse.libraryManagementSystem.dao.custom.impl.BranchDaoImpl;
import lk.ijse.libraryManagementSystem.dto.BranchDto;
import lk.ijse.libraryManagementSystem.entity.Branch;

import java.util.ArrayList;
import java.util.List;

public class BranchBoImpl implements BranchBo {
   BranchDao dao=new BranchDaoImpl();
    @Override
    public String generateNewBranchId() {

        Branch branch = dao.generateNewId();

        if (!(branch.getId() == null)) {
            return splitId(branch.getId());
        }
        return splitId(null);

    }

    @Override
    public boolean saveBranch(BranchDto branchDto) {

        boolean saved = dao.save(new Branch(branchDto.getId(), branchDto.getName(), branchDto.getAddress(), branchDto.getContact(), branchDto.getEmail(), branchDto.getUsers(), branchDto.getBooks()));
        return saved;
    }

    @Override
    public List<BranchDto> loadAllBranch() {

        List<Branch> branchList = dao.getAll();
        ArrayList<BranchDto> branchDtos = new ArrayList<>();
        for (Branch branch : branchList) {
            branchDtos.add(new BranchDto(branch.getId(), branch.getName(), branch.getAddress(), branch.getContact(), branch.getEmail(), branch.getUsers(), branch.getBooks()));
        }
        return branchDtos;

    }

    @Override
    public boolean updateBranch(BranchDto branchDto) {

        boolean saved = dao.update(new Branch(branchDto.getId(), branchDto.getName(), branchDto.getAddress(), branchDto.getContact(), branchDto.getEmail(), branchDto.getUsers(), branchDto.getBooks()));

        return saved;
    }

    @Override
    public boolean deleteBranch(BranchDto dto) {

        boolean deleted = dao.delete(new Branch(dto.getId(),dto.getName(),dto.getAddress(),dto.getContact(),dto.getEmail(),dto.getUsers(),dto.getBooks()));

        return deleted;
    }

    @Override
    public  BranchDto getBranch(String id) {
        Branch branch = dao.getBranch(id);
        return new BranchDto(branch.getId(),branch.getName(),branch.getAddress(),branch.getContact(),branch.getEmail(),branch.getUsers(),branch.getBooks());
    }

    public String splitId(String currentBranchId) {
        if(currentBranchId!=null){
            String [] split= currentBranchId.split("Branch0");
            int id=Integer.parseInt(split[1]);

            id++;
            if(id<10)
            {return "Branch00"+id;}
            else{
                return "Branch0"+id;
            }
        }
        else {
            return "Branch001";
        }
    }
}
