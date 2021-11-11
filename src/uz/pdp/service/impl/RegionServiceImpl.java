package uz.pdp.service.impl;

import uz.pdp.model.Region;
import uz.pdp.service.interfaces.RegionService;

import java.util.ArrayList;
import java.util.UUID;

public class RegionServiceImpl implements RegionService {

    private static ArrayList<Region> regions;

    public RegionServiceImpl() {
        regions=new ArrayList<>();
        regions.add(new Region("Tashkent"));
        regions.add(new Region("Bukhara"));
        regions.add(new Region("Samarqand"));
    }

    @Override
    public ArrayList<Region> getAll() {
        return regions;
    }

    @Override
    public boolean add(Region region) {
        return regions.add(region);
    }

    @Override
    public Region getById(UUID uuid) {
        for (Region region : regions)
            if(region.getId().equals(uuid))
                return region;
            return null;
    }

    @Override
    public boolean checkByName(String name) {
        for (Region region : regions)
            if(region.getName().equals(name))
                return true;
        return false;
    }

    public static Region getByName(String name){
        for (Region region : regions)
            if(region.getName().equals(name))
                return region;
        return null;
    }

}
