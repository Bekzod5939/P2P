package uz.pdp.service.interfaces;

import uz.pdp.model.User;
import uz.pdp.service.base.BaseService;

import java.util.ArrayList;
import java.util.UUID;

public interface UserService extends BaseService<User,UUID> {

    @Override
    ArrayList<User> getAll();

    boolean add(User user);
    ArrayList<User> getUsers();

    boolean isExist(String phone);

    User login(String phone,String password);

    User getUser(String phone);

    User getById(UUID id);

}
