package lk.ijse.libraryManagementSystem.bo.custom.impl;

import lk.ijse.libraryManagementSystem.bo.custom.AdminBo;
import lk.ijse.libraryManagementSystem.dao.custom.AdminDao;
import lk.ijse.libraryManagementSystem.dao.custom.impl.AdminDaoImpl;
import lk.ijse.libraryManagementSystem.dto.AdminDto;
import lk.ijse.libraryManagementSystem.entity.Admin;

import java.util.ArrayList;
import java.util.List;

public class AdminBoImpl implements AdminBo {
AdminDao adminDao=new AdminDaoImpl();
    @Override
    public ArrayList<AdminDto> getAllAdmins() {

        List<Admin> adminList = adminDao.getAll();
        ArrayList<AdminDto> adminDtos = new ArrayList<>();
        for (Admin admin : adminList) {
            adminDtos.add(new AdminDto(admin.getEmail(), admin.getPassword(), admin.getName(), admin.getAddress(), admin.getContact()));
        }
        return adminDtos;

    }
}
