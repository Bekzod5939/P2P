package uz.pdp.service.impl;

import uz.pdp.model.District;
import uz.pdp.service.interfaces.DistrictService;

import java.util.ArrayList;
import java.util.UUID;

public class DistrictServiceImpl implements DistrictService {
    private static ArrayList<District> districts;

    public DistrictServiceImpl() {
        districts=new ArrayList<>();
        districts.add(new District("Shayhontohur",RegionServiceImpl.getByName("Tashkent").getId()));
        districts.add(new District("Olot",RegionServiceImpl.getByName("Bukhara").getId()));
        districts.add(new District("Kattaqo'rg'on",RegionServiceImpl.getByName("Samarqand").getId()));
    }

    @Override
    public ArrayList<District> getAll() {
        return districts;
    }

    @Override
    public boolean add(District district) {
        return districts.add(district);
    }

    @Override
    public District getById(UUID uuid) {
        for (District district : districts)
            if(district.getId().equals(uuid))
                return district;
        return null;
    }

    @Override
    public ArrayList<District> getByRegionId(UUID regionId) {
        ArrayList<District> result=new ArrayList<>();
        for (District district : districts)
            if(district.getRegionId().equals(regionId))
                result.add(district);
        return result;
    }

    @Override
    public boolean checkByNameAndRegionId(String name, UUID regionId) {
        for (District district : districts)
            if(district.getName().equals(name)&&district.getRegionId().equals(regionId))
                return true;
        return false;
    }


}
