package uz.pdp.service.impl;

import uz.pdp.enums.RoleEnum;
import uz.pdp.model.User;
import uz.pdp.service.interfaces.UserService;

import java.util.ArrayList;
import java.util.UUID;

public class UserServiceImpl implements UserService {

    private static ArrayList<User> users = new ArrayList<>();

    public UserServiceImpl() {
        users.add(new User("admin","1","1", RoleEnum.ADMIN));
    }

    @Override
    public boolean add(User user) {
        for (User item : users) {
            if (item.getPhone().equals(user.getPhone()))
                return false;
        }
        users.add(user);
        return true;
    }

    @Override
    public ArrayList<User> getUsers() {
        return users;
    }

    @Override
    public ArrayList<User> getAll() {
        return users;
    }

    @Override
    public boolean isExist(String phone) {
        for (User user : users) {
            if (user.getPhone().equals(phone))
                return true;
        }
        return false;
    }

    @Override
    public User login(String phone, String password) {
        for (User user : users)
            if (user.getPhone().equals(phone) && user.getPassword().equals(password))
                return user;
        return null;
    }

    @Override
    public User getUser(String phone) {
        for (User user : users) {
            if(user.getPhone().equals(phone))
                return user;
        }
        return null;
    }

    @Override
    public User getById(UUID id) {
        for (User user : users)
            if(user.getId().equals(id))
                return user;

        return null;
    }

    public static User getOwner(){
        for (User user : users) {
            if(user.getPhone().equals("1"))
                return user;
        }
        return null;
    }


}
