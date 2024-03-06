package lk.ijse.libraryManagementSystem.bo;

import lk.ijse.libraryManagementSystem.dto.UserDto;

import java.util.ArrayList;

public interface UserBo {
    public boolean saveUser(UserDto dto);
    public ArrayList<UserDto> getAllUsers();
}
