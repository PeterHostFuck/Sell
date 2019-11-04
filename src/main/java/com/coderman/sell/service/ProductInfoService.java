package com.coderman.sell.service;

import com.coderman.sell.dto.OrderItem;
import com.coderman.sell.modal.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品service
 * Created by zhangyukang on 2019/11/4 11:30
 */
public interface ProductInfoService {

    /**
     * 查询所有的在架商品
     * @return
     */
    List<ProductInfo> findAllUp();

    /**
     * 保存更新
     * @param productInfo
     * @return
     */
    ProductInfo save(ProductInfo productInfo);

    /**
     * 查询所有的商品(分页)
     * @return
     */
    Page<ProductInfo> findAll(Pageable pageable);



    ProductInfo findOne(String productId);

    /**
     * 增加库存
     */
    void increaseStock(List<OrderItem> list);

    /**
     * 减少库存
     */
    void decreaseStock(List<OrderItem> list);


}
