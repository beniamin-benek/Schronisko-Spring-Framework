package com.ben.schronisko2.repository;

import com.ben.schronisko2.model.Category;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class CategoryRepository {

    private List<Category> categories = new LinkedList<>();

    public CategoryRepository() {
        categories.add(0, new Category("Pies"));
        categories.add(1,new Category("Kot"));
        categories.add(2,new Category("Inne"));
    }

    public List<Category> findAll() {
        return categories;
    }

    private boolean checkIfExists(String name){
        for (Category category : categories) {
            if (category.getName().equals(name))
                return true;
        }
        return false;
    }

    public void addCategory(String name) {
        if (!checkIfExists(name))
            categories.add(categories.size(),new Category(name));
    }

}
