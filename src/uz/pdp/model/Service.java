package uz.pdp.model;

import uz.pdp.model.base.BaseModel;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.UUID;

public class Service extends BaseModel {
    private String name;
    private BigDecimal balance;
    private UUID categoryId;
    private HashMap<String,String> objects=new HashMap<>();

    public HashMap<String, String> getObjects() {
        return objects;
    }

    public void setObjects(HashMap<String, String> objects) {
        this.objects = objects;
    }

    public  void addObject(String key,String value){
        this.objects.put(key,value);
    }

    public boolean checkObjectByKey(String key){
        return objects.containsKey(key);
    }

    public Service(String name, BigDecimal balance, UUID categoryId) {
        this.name = name;
        this.balance = balance;
        this.categoryId = categoryId;
    }

    public Service() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }
}
