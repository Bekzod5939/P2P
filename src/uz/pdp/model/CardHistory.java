package uz.pdp.model;

import uz.pdp.model.base.BaseModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class CardHistory extends BaseModel {
    private UUID from;
    private UUID to;
    private LocalDateTime time;
    private BigDecimal price;

    public CardHistory(UUID from, UUID to, BigDecimal price) {
        this.time=LocalDateTime.now();
        this.from = from;
        this.to = to;
        this.price = price;
    }

    public CardHistory() {
    }

    public UUID getFrom() {
        return from;
    }

    public void setFrom(UUID from) {
        this.from = from;
    }

    public UUID getTo() {
        return to;
    }

    public void setTo(UUID to) {
        this.to = to;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
