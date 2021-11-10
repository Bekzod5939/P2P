package uz.pdp.service.interfaces;

import uz.pdp.model.Commission;
import uz.pdp.service.base.BaseService;

import java.util.ArrayList;
import java.util.UUID;

public interface CommissionService extends BaseService<Commission, UUID> {


    @Override
    ArrayList<Commission> getAll();

    @Override
    boolean add(Commission commission);

    @Override
    Commission getById(UUID uuid);

    Commission getByServiceId(UUID serviceId);

    boolean editCommission(Commission commission);
}
