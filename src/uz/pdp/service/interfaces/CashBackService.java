package uz.pdp.service.interfaces;

import uz.pdp.model.CashBack;
import uz.pdp.service.base.BaseService;

import java.util.ArrayList;
import java.util.UUID;

public interface CashBackService extends BaseService<CashBack, UUID> {


    @Override
    ArrayList<CashBack> getAll();

    @Override
    boolean add(CashBack cashBack);

    @Override
    CashBack getById(UUID uuid);

    boolean editCashBack(CashBack cashBack);
}
