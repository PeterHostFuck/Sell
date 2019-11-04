package com.coderman.sell.service;

import com.coderman.sell.modal.ProductCategory;

import java.util.List;

/**
 * 商品类目service
 * Created by zhangyukang on 2019/11/4 10:45
 */
public interface ProductCategoryService {


    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeIds);

    /**
     * @param id
     * @return
     */
    ProductCategory findOne(Integer id);
    /**
     * 更新保存
     * @param productCategory
     * @return
     */
    ProductCategory save(ProductCategory productCategory);

    /**
     * 查询所有的商品类目
     * @return
     */
    List<ProductCategory> findAll();

}
