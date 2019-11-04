package com.coderman.sell.service.impl;

import com.coderman.sell.dao.ProductInfoRepository;
import com.coderman.sell.dto.OrderItem;
import com.coderman.sell.exception.SellException;
import com.coderman.sell.modal.ProductInfo;
import com.coderman.sell.myenums.ProductStatusEnum;
import com.coderman.sell.myenums.ResultEnum;
import com.coderman.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * Created by zhangyukang on 2019/11/4 11:32
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository repository;


    @Override
    public List<ProductInfo> findAllUp() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo findOne(String productId) {
        return  repository.findByProductId(productId);
    }

    @Override
    public void increaseStock(List<OrderItem> list) {

    }

    @Transactional
    @Override
    public void decreaseStock(List<OrderItem> list) {
        for (OrderItem orderItem : list) {
            String productId = orderItem.getProductId();
            ProductInfo byProductId = repository.findByProductId(productId);
            if(byProductId==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result= byProductId.getProductStock()-orderItem.getProductQuantity();
            if(result<0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            byProductId.setProductStock(result);
            repository.save(byProductId);
        }
    }
}
