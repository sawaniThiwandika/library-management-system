package lk.ijse.libraryManagementSystem.bo;

import lk.ijse.libraryManagementSystem.dto.AdminDto;

import java.util.ArrayList;

public interface AdminBo extends SuperBo {
    public ArrayList<AdminDto> getAllAdmins();
}
