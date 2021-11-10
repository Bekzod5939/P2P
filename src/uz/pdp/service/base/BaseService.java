package uz.pdp.service.base;

import java.util.ArrayList;

public interface BaseService<TYPE,ID> {
    ArrayList<TYPE> getAll();

    boolean add(TYPE type);

    TYPE getById(ID id);
}
