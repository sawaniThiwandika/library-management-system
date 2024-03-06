package lk.ijse.libraryManagementSystem.bo.impl;

import lk.ijse.libraryManagementSystem.bo.BranchBo;
import lk.ijse.libraryManagementSystem.config.FactoryConfiguration;
import lk.ijse.libraryManagementSystem.dao.BranchDao;
import lk.ijse.libraryManagementSystem.dao.impl.BranchDaoImpl;
import lk.ijse.libraryManagementSystem.dto.BranchDto;
import lk.ijse.libraryManagementSystem.entity.Branch;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BranchBoImpl implements BranchBo {
   BranchDao dao=new BranchDaoImpl();
    @Override
    public String generateNewBranchId() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Branch branch = dao.generateNewId(session);
        session.close();
        if(!(branch.getId()==null)) {
            return splitId(branch.getId());
        }
        return splitId(null);

    }

    @Override
    public boolean saveBranch(BranchDto branchDto) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        boolean saved = dao.save(session, new Branch(branchDto.getId(), branchDto.getName(), branchDto.getAddress(), branchDto.getContact(), branchDto.getEmail(),branchDto.getUsers(),branchDto.getBooks()));
        session.close();
        return saved;
    }

    @Override
    public List<BranchDto> loadAllBranch() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        List<Branch> branchList = dao.getAll(session);
        ArrayList<BranchDto> branchDtos = new ArrayList<>();
        for (Branch branch:branchList){
            branchDtos.add(new BranchDto(branch.getId(),branch.getName(),branch.getAddress(),branch.getContact(),branch.getEmail(),branch.getUsers(),branch.getBooks()));
        }
        return branchDtos;

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
