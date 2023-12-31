package com.example.case6.service.impl;

import com.example.case6.model.Category;
import com.example.case6.repository.ICategoryRepo;
import com.example.case6.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryRepo iCategoryRepo;
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Category> findAll() {
        return iCategoryRepo.findAll();
    }

    @Override
    public Optional<Category> findById(Long aLong) {
        return iCategoryRepo.findById(aLong);
    }

    @Override
    public void save(Category category) {
        iCategoryRepo.save(category);
    }

    @Override
    public void delete(Long aLong) {
        iCategoryRepo.deleteById(aLong);
    }

    @Override
    public List<Category> getTenCategoriesPage(int offset) {
        String hql = "FROM Category";
        List<Category> result = entityManager.createQuery(hql, Category.class)
                .setFirstResult(offset)
                .setMaxResults(10)
                .getResultList();
        return result;
    }
}
