package uz.pdp.model;

import uz.pdp.model.base.BaseModel;

import java.util.UUID;

public class District extends BaseModel {
    private String name;
    private UUID regionId;

    public District(String name, UUID regionId) {
        this.name = name;
        this.regionId = regionId;
    }

    public District() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getRegionId() {
        return regionId;
    }

    public void setRegionId(UUID regionId) {
        this.regionId = regionId;
    }
}
