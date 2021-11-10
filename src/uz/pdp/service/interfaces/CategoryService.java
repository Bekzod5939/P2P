package uz.pdp.service.interfaces;

import uz.pdp.model.Category;
import uz.pdp.service.base.BaseService;

import java.util.ArrayList;
import java.util.UUID;

public interface CategoryService extends BaseService<Category, UUID> {
    @Override
    ArrayList<Category> getAll();

    @Override
    boolean add(Category category);

    @Override
    Category getById(UUID uuid);
}
