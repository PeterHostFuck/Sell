package com.coderman.sell.dao;

import com.coderman.sell.modal.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by zhangyukang on 2019/11/4 11:29
 */
public interface ProductInfoRepository  extends JpaRepository<ProductInfo,Integer>{
    /**查询在架商品*/
    List<ProductInfo> findByProductStatus(Integer status);

    ProductInfo findByProductId(String productId);
}
