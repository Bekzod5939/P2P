package uz.pdp.service.impl;

import uz.pdp.enums.CategoryEnum;
import uz.pdp.model.CashBack;
import uz.pdp.service.interfaces.CashBackService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

public class CashBackServiceImpl implements CashBackService {

    private static ArrayList<CashBack> cashBacks;

    public CashBackServiceImpl() {
        cashBacks = new ArrayList<>();
        cashBacks.add(new CashBack(ServiceServiceImpl.getByName(CategoryEnum.TRANSFER.name()).getId(), BigDecimal.valueOf(0)));
        cashBacks.add(new CashBack(ServiceServiceImpl.getByName("UCELL").getId(), BigDecimal.valueOf(0)));
        cashBacks.add(new CashBack(ServiceServiceImpl.getByName("KIVI").getId(), BigDecimal.valueOf(0)));
        cashBacks.add(new CashBack(ServiceServiceImpl.getByName("ELECTRICITY").getId(), BigDecimal.valueOf(0)));
    }


    public static CashBack getByServiceId(UUID serviceId){
        for (CashBack cashBack : cashBacks)
            if(cashBack.getServiceId().equals(serviceId))
                return cashBack;
            return null;
    }



    @Override
    public ArrayList<CashBack> getAll() {
        return cashBacks;
    }

    @Override
    public boolean add(CashBack cashBack) {
        cashBacks.add(cashBack);
        return true;
    }

    @Override
    public CashBack getById(UUID uuid) {
        for (CashBack cashBack : cashBacks)
            if(cashBack.getId().equals(uuid))
                return cashBack;
        return null;
    }

    @Override
    public boolean editCashBack(CashBack cashBack) {
        if (cashBacks.removeIf(cashBack1 -> cashBack1.getId().equals(cashBack.getId()))) {
            cashBacks.add(cashBack);
            return true;
        }
        return false;
    }


}
