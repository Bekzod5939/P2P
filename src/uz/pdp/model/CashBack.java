package uz.pdp.model;

import uz.pdp.model.base.BaseModel;

import java.math.BigDecimal;
import java.util.UUID;

public class CashBack extends BaseModel {
    private UUID serviceId;
    private BigDecimal amount;

    public CashBack(UUID serviceId, BigDecimal amount) {
        this.serviceId = serviceId;
        this.amount = amount;
    }

    public CashBack() {
    }

    public UUID getServiceId() {
        return serviceId;
    }

    public void setServiceId(UUID serviceId) {
        this.serviceId = serviceId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
