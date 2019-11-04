package com.coderman.sell.dao;

import com.coderman.sell.modal.ProductCategory;
import com.coderman.sell.service.ProductCategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by zhangyukang on 2019/11/4 10:41
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceTest {
    @Autowired
    private ProductCategoryService productCategoryService;

    @Test
    public void testfindONe(){
        List<ProductCategory> all = productCategoryService.findAll();
        System.out.println(all);
    }

}