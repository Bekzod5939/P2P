package uz.pdp.model;

import uz.pdp.model.base.BaseModel;

public class Region extends BaseModel {
    private String name;

    public Region(String name) {
        this.name = name;
    }

    public Region() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
