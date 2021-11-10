package uz.pdp.model.base;

import java.util.UUID;

public class BaseModel {
    private UUID id;

    public BaseModel() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
