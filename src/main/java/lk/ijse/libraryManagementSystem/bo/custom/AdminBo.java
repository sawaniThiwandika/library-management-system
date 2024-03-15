package lk.ijse.libraryManagementSystem.bo.custom;

import lk.ijse.libraryManagementSystem.bo.SuperBo;
import lk.ijse.libraryManagementSystem.dto.AdminDto;

import java.util.ArrayList;

public interface AdminBo extends SuperBo {
    public ArrayList<AdminDto> getAllAdmins();
}
