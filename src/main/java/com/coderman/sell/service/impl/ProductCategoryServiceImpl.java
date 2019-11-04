package com.coderman.sell.service.impl;

import com.coderman.sell.dao.ProductCategoryRepository;
import com.coderman.sell.modal.ProductCategory;
import com.coderman.sell.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangyukang on 2019/11/4 10:47
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository repository;

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeIds) {
        return repository.findByCategoryTypeIn(categoryTypeIds);
    }

    @Override
    public ProductCategory findOne(Integer id) {
        return repository.findByCategoryId(id);
    }


    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }
}

