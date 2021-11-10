package uz.pdp.service.interfaces;

import uz.pdp.model.Service;
import uz.pdp.service.base.BaseService;

import java.util.ArrayList;
import java.util.UUID;

public interface ServiceService extends BaseService<Service, UUID> {
    @Override
    ArrayList<Service> getAll();

    @Override
    boolean add(Service service);

    @Override
    Service getById(UUID uuid);

    ArrayList<Service> getByCategoryId(UUID categoryId);

    boolean editService(Service service);
}
