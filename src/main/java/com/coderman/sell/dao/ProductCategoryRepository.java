package com.coderman.sell.dao;

import com.coderman.sell.modal.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by zhangyukang on 2019/11/4 10:38
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer>{

    /**
     * 根据商品类目id查询所有类目
     * @param categoryIds
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryIds);

    ProductCategory findByCategoryId(Integer id);

}
