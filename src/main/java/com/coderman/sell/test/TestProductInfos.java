package com.coderman.sell.test;

import com.coderman.sell.modal.ProductInfo;
import com.coderman.sell.service.impl.ProductInfoServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Created by zhangyukang on 2019/11/4 11:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestProductInfos {
    @Autowired
    private ProductInfoServiceImpl productInfoService;


    @Test
    public void findAllUp(){
        List<ProductInfo> allUp = productInfoService.findAllUp();
        System.out.println(allUp);
    }

    @Test
    public void testUpate(){
        List<ProductInfo> allUp = productInfoService.findAllUp();
        ProductInfo productInfo = allUp.get(0);
        productInfo.setProductIcon("http://wwww.*****.png");
        productInfoService.save(productInfo);
    }
    @Test
    public void testSave(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setCategoryType(1);
        productInfo.setProductId(UUID.randomUUID().toString().substring(0,5));
        productInfo.setProductDescription("世上最好吃的麻辣小龙虾");
        productInfo.setProductStock(15);
        productInfo.setProductPrice(new BigDecimal(0.2));
        productInfo.setProductIcon("http://www.88888.jpg");
        productInfo.setProductName("麻辣小龙虾");
        productInfo.setProductStatus(0);
        ProductInfo save = productInfoService.save(productInfo);
        System.out.println(save);
    }
}
