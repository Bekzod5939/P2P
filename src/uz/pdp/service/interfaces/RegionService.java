package uz.pdp.service.interfaces;

import uz.pdp.model.Region;
import uz.pdp.service.base.BaseService;

import java.util.ArrayList;
import java.util.UUID;

public interface RegionService extends BaseService<Region, UUID> {
    @Override
    ArrayList<Region> getAll();

    @Override
    boolean add(Region region);

    @Override
    Region getById(UUID uuid);

    boolean checkByName(String name);
}
