package uz.pdp.service.impl;

import uz.pdp.enums.CategoryEnum;
import uz.pdp.model.CashBack;
import uz.pdp.model.Commission;
import uz.pdp.service.interfaces.CommissionService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

public class CommissionServiceImpl implements CommissionService {

    private static ArrayList<Commission> commissions;

    public CommissionServiceImpl() {
        commissions = new ArrayList<>();
        commissions.add(new Commission(ServiceServiceImpl.getByName(CategoryEnum.TRANSFER.name()).getId(), BigDecimal.valueOf(0)));
        commissions.add(new Commission(ServiceServiceImpl.getByName("UCELL").getId(), BigDecimal.valueOf(0)));
        commissions.add(new Commission(ServiceServiceImpl.getByName("KIVI").getId(), BigDecimal.valueOf(0)));
        commissions.add(new Commission(ServiceServiceImpl.getByName("ELECTRICITY").getId(), BigDecimal.valueOf(0)));

    }

    @Override
    public ArrayList<Commission> getAll() {
        return commissions;
    }

    @Override
    public boolean add(Commission commission) {
        commissions.add(commission);
        return true;
    }

    @Override
    public Commission getById(UUID uuid) {
        for (Commission commission : commissions)
            if(commission.getId().equals(uuid))
                return commission;
        return null;
    }

    @Override
    public Commission getByServiceId(UUID serviceId) {
        for (Commission commission : commissions)
            if(commission.getServiceId().equals(serviceId))
                return commission;
        return null;
    }

    @Override
    public boolean editCommission(Commission commission) {
        if(commissions.removeIf(commission1 -> commission1.getId().equals(commission.getId()))){
            commissions.add(commission);
            return true;
        }
        return false;
    }

    public static Commission getStaticByServiceId(UUID serviceId){
        for (Commission commission : commissions)
            if(commission.getServiceId().equals(serviceId))
                return commission;
            return null;


    }

}
