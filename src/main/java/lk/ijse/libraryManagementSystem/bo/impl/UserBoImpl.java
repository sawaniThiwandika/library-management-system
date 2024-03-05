package lk.ijse.libraryManagementSystem.bo.impl;

import lk.ijse.libraryManagementSystem.bo.UserBo;
import lk.ijse.libraryManagementSystem.dao.UserDao;
import lk.ijse.libraryManagementSystem.dao.impl.UserDaoImpl;
import lk.ijse.libraryManagementSystem.dto.UserDto;
import lk.ijse.libraryManagementSystem.entity.User;

public class UserBoImpl implements UserBo {
   UserDao userDao=new UserDaoImpl();
    @Override
    public boolean saveUser(UserDto dto) {
        return userDao.save(new User(dto.getName(),dto.getEmail(),dto.getBranch(),dto.getPassword(),dto.getTransactions()));
    }
}
