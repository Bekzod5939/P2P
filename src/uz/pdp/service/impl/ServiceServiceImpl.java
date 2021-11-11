package uz.pdp.service.impl;

import uz.pdp.enums.CategoryEnum;
import uz.pdp.model.Service;
import uz.pdp.service.interfaces.ServiceService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

public class ServiceServiceImpl implements ServiceService {

   private static ArrayList<Service> services;

    public ServiceServiceImpl() {
        services = new ArrayList<>();
        services.add(new Service(CategoryEnum.TRANSFER.name(),
                BigDecimal.valueOf(0),
                CategoryServiceImpl.getCategoryByName(CategoryEnum.TRANSFER.name()).getId()));
        services.add(new Service("UCELL",
                BigDecimal.valueOf(0),
                CategoryServiceImpl.getCategoryByName(CategoryEnum.MOBILE_OPERATORS.name()).getId()));
        services.add(new Service("ELECTRICITY",
                BigDecimal.valueOf(0),
                CategoryServiceImpl.getCategoryByName(CategoryEnum.COMMUNAL.name()).getId()));
        services.add(new Service("KIVI",
                BigDecimal.valueOf(0),
                CategoryServiceImpl.getCategoryByName(CategoryEnum.WALLETS.name()).getId()));
    }

    @Override
    public ArrayList<Service> getAll() {
        return services;
    }

    @Override
    public boolean add(Service service) {
        services.add(service);
        return true;
    }

    @Override
    public Service getById(UUID uuid) {
        for (Service service : services)
            if(service.getId().equals(uuid))
                return service;

        return null;
    }

    @Override
    public ArrayList<Service> getByCategoryId(UUID categoryId) {
        ArrayList<Service> serviceArrayList=new ArrayList<>();
        for (Service service : services)
            if(service.getCategoryId().equals(categoryId))
                serviceArrayList.add(service);
        return serviceArrayList;
    }

    @Override
    public boolean editService(Service service) {
        if (services.removeIf(service1 -> service1.getId().equals(service.getId()))) {
            services.add(service);
            return true;
        }
        return false;
    }

    @Override
    public boolean checkByNameAndCategoryId(String name, UUID categoryId) {
        for (Service service : services)
            if(service.getName().equals(name)&&service.getCategoryId().equals(categoryId))
                return true;
        return false;
    }

    public static Service getByName(String name){
        for (Service service : services)
            if(service.getName().equals(name))
                return service;
            return null;

    }
}
