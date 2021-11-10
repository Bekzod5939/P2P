package uz.pdp.model;

import uz.pdp.model.base.BaseModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Card extends BaseModel {
    private String name;
    private BigDecimal amount;
    private LocalDate expireDate;
    private LocalDate issueDate;
    private UUID ownerId;
    private BigDecimal cashBack;
    private String cardNum;

    public Card(String name, UUID ownerId,BigDecimal amount,BigDecimal cashBack,String cardNum) {
        this.cardNum=cardNum;
        this.cashBack=cashBack;
        this.amount=amount;
        this.name = name;
        this.expireDate = LocalDate.now().plusYears(4);
        this.issueDate = LocalDate.now();
        this.ownerId = ownerId;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public Card() {
    }

    public BigDecimal getCashBack() {
        return cashBack;
    }

    public void setCashBack(BigDecimal cashBack) {
        this.cashBack = cashBack;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }
}
