package lk.ijse.libraryManagementSystem.bo.custom.impl;

import lk.ijse.libraryManagementSystem.bo.custom.UserBo;
import lk.ijse.libraryManagementSystem.dao.custom.UserDao;
import lk.ijse.libraryManagementSystem.dao.custom.impl.UserDaoImpl;
import lk.ijse.libraryManagementSystem.dto.UserDto;
import lk.ijse.libraryManagementSystem.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserBoImpl implements UserBo {
   UserDao userDao=new UserDaoImpl();
    @Override
    public boolean saveUser(UserDto dto) {

        boolean isSaved = userDao.save(new User(dto.getName(), dto.getEmail(), dto.getBranch(), dto.getPassword(), dto.getTransactions(), dto.getContact()));

        return isSaved;
    }

    @Override
    public ArrayList<UserDto> getAllUsers() {

        List<User> users = userDao.getAll();
        ArrayList<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(new UserDto(user.getName(), user.getEmail(), user.getBranch(), user.getPassword(), user.getTransactions(), user.getContact()));
        }
        return userDtos;

    }

    @Override
    public boolean updateUser(UserDto dto) {

        boolean isUpdated = userDao.update(new User(dto.getName(), dto.getEmail(), dto.getBranch(), dto.getPassword(), dto.getTransactions(), dto.getContact()));
        return isUpdated;
    }

    @Override
    public UserDto searchUser(String email) {

        User user = userDao.search(email);
        return new UserDto(user.getName(), user.getEmail(), user.getBranch(), user.getPassword(), user.getTransactions(), user.getContact());
    }

    @Override
    public boolean deleteUser(UserDto dto) {
        return userDao.delete(new User(dto.getName(), dto.getEmail(), dto.getBranch(), dto.getPassword(), dto.getTransactions(), dto.getContact()));
    }
}
