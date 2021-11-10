package uz.pdp.model;

import uz.pdp.model.base.BaseModel;

import java.math.BigDecimal;
import java.util.UUID;

public class Service extends BaseModel {
    private String name;
    private BigDecimal balance;
    private UUID categoryId;

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
