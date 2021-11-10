package uz.pdp.service.impl;

import uz.pdp.enums.CategoryEnum;
import uz.pdp.model.Category;
import uz.pdp.service.interfaces.CategoryService;

import java.util.ArrayList;
import java.util.UUID;

public class CategoryServiceImpl implements CategoryService {
    private static ArrayList<Category> categories;

    public CategoryServiceImpl() {
        categories =new ArrayList<>();
        categories.add(new Category(CategoryEnum.TRANSFER.name()));
        categories.add(new Category(CategoryEnum.MOBILE_OPERATORS.name()));
        categories.add(new Category(CategoryEnum.WALLETS.name()));
        categories.add(new Category(CategoryEnum.COMMUNAL.name()));
    }

    @Override
    public ArrayList<Category> getAll() {
        return categories;
    }

    @Override
    public boolean add(Category category) {
        categories.add(category);
        return true;
    }

    @Override
    public Category getById(UUID uuid) {
        for (Category category : categories)
            if(category.getId().equals(uuid))
                return category;
            return null;

    }

    public static Category getCategoryByName(String name){
        for (Category category : categories)
            if(category.getName().equals(name))
                return category;
            return null;
    }
}
