package uz.pdp.service.interfaces;

import uz.pdp.model.District;
import uz.pdp.service.base.BaseService;

import java.util.ArrayList;
import java.util.UUID;

public interface DistrictService extends BaseService<District, UUID> {

    @Override
    ArrayList<District> getAll();

    @Override
    boolean add(District district);

    @Override
    District getById(UUID uuid);


    public ArrayList<District> getByRegionId(UUID regionId);

    boolean checkByNameAndRegionId(String name,UUID regionId);
}
