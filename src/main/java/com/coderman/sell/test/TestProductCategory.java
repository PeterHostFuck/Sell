package com.coderman.sell.test;

import com.coderman.sell.modal.ProductCategory;
import com.coderman.sell.service.impl.ProductCategoryServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zhangyukang on 2019/11/4 10:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestProductCategory {

    @Autowired
    private ProductCategoryServiceImpl ProductCategoryServiceImpl;


    @Test
    public void testfindByCategoryTypeIn(){
        List<ProductCategory> byCategoryTypeIn = ProductCategoryServiceImpl.findByCategoryTypeIn(Arrays.asList(1, 3, 4));
        System.out.println(byCategoryTypeIn);
    }

    @Test
    public void testFindOne(){
        ProductCategory one = ProductCategoryServiceImpl.findOne(10);
        System.out.println(one);
    }

    @Test
    public void testSave(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryType(5);
        productCategory.setCategoryName("小吃");
        ProductCategory save = ProductCategoryServiceImpl.save(productCategory);
        System.out.println(save);
    }
    @Test
    public void testFindAll(){
        List<ProductCategory> all = ProductCategoryServiceImpl.findAll();
        System.out.println(all);
    }
    @Test
    public void testUpdateCategory(){
        List<ProductCategory> all = ProductCategoryServiceImpl.findAll();
        if(all.size()>0){
            ProductCategory productCategory = all.get(0);
            productCategory.setCategoryName("热销榜~");
            ProductCategory save = ProductCategoryServiceImpl.save(productCategory);
            System.out.println(save);
        }
    }



}
