package lk.ijse.libraryManagementSystem.bo.custom;

import lk.ijse.libraryManagementSystem.bo.SuperBo;
import lk.ijse.libraryManagementSystem.dto.UserDto;

import java.util.ArrayList;

public interface UserBo extends SuperBo {
    public boolean saveUser(UserDto dto);
    public ArrayList<UserDto> getAllUsers();
    public boolean updateUser(UserDto dto);
    public UserDto searchUser(String email);
    public boolean deleteUser(UserDto dto);
}
