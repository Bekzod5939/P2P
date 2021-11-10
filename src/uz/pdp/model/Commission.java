package uz.pdp.model;

import uz.pdp.model.base.BaseModel;

import java.math.BigDecimal;
import java.util.UUID;

public class Commission extends BaseModel {
    private UUID serviceId;
    private BigDecimal amount;

    public Commission(UUID serviceId, BigDecimal amount) {
        this.serviceId = serviceId;
        this.amount = amount;
    }

    public Commission() {
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
