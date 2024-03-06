package lk.ijse.libraryManagementSystem.bo.impl;

import lk.ijse.libraryManagementSystem.bo.AdminBo;
import lk.ijse.libraryManagementSystem.config.FactoryConfiguration;
import lk.ijse.libraryManagementSystem.dao.AdminDao;
import lk.ijse.libraryManagementSystem.dao.impl.AdminDaoImpl;
import lk.ijse.libraryManagementSystem.dto.AdminDto;
import lk.ijse.libraryManagementSystem.entity.Admin;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class AdminBoImpl implements AdminBo {
AdminDao adminDao=new AdminDaoImpl();
    @Override
    public ArrayList<AdminDto> getAllAdmins() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        List<Admin> adminList = adminDao.getAll(session);
        ArrayList<AdminDto> adminDtos = new ArrayList<>();
        for (Admin admin:adminList){
            adminDtos.add(new AdminDto(admin.getEmail(), admin.getPassword(),admin.getName(),admin.getAddress(),admin.getContact()));
        }
        return adminDtos;

    }
}
