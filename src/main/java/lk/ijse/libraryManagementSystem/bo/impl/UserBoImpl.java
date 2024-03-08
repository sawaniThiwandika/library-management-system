package lk.ijse.libraryManagementSystem.bo.impl;

import lk.ijse.libraryManagementSystem.bo.UserBo;
import lk.ijse.libraryManagementSystem.config.FactoryConfiguration;
import lk.ijse.libraryManagementSystem.dao.UserDao;
import lk.ijse.libraryManagementSystem.dao.impl.UserDaoImpl;
import lk.ijse.libraryManagementSystem.dto.UserDto;
import lk.ijse.libraryManagementSystem.entity.User;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class UserBoImpl implements UserBo {
   UserDao userDao=new UserDaoImpl();
    @Override
    public boolean saveUser(UserDto dto) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        boolean isSaved = userDao.save(session, new User(dto.getName(), dto.getEmail(), dto.getBranch(), dto.getPassword(), dto.getTransactions(), dto.getContact()));
        session.close();
        return isSaved;
    }

    @Override
    public ArrayList<UserDto> getAllUsers() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        List<User> users = userDao.getAll(session);
        session.close();
        ArrayList<UserDto> userDtos = new ArrayList<>();
        for (User user:users){
            userDtos.add(new UserDto(user.getName(),user.getEmail(),user.getBranch(),user.getPassword(),user.getTransactions(),user.getContact()));
        }
        return userDtos;

    }

    @Override
    public boolean updateUser(UserDto dto) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        boolean isUpdated = userDao.update(session, new User(dto.getName(), dto.getEmail(), dto.getBranch(), dto.getPassword(), dto.getTransactions(), dto.getContact()));
        session.close();
        return isUpdated;
    }
}
