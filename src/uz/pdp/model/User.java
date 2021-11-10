package uz.pdp.model;

import uz.pdp.enums.RoleEnum;
import uz.pdp.model.base.BaseModel;

public class User extends BaseModel {
    private String name;
    private String phone;
    private String password;
    private RoleEnum role;

    public User(String name, String phone, String password, RoleEnum role) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public User() {
    }

    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
