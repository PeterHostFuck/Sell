package com.coderman.sell.dto;

import lombok.Data;

/**
 * 购物车
 * Created by zhangyukang on 2019/11/4 20:29
 */
@Data
public class OrderItem {
    private String productId;

    private Integer productQuantity;

}
