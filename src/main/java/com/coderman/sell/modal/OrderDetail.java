package com.coderman.sell.modal;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 订单详情
 * Created by zhangyukang on 2019/11/4 19:23
 */
@Data
@Entity
public class OrderDetail {

    /** 订单详情主键*/
    @Id
    private String detailId;

    /** 订单Id*/
    private String orderId;

    /** 商品Id*/
    private String productId;

    /** 商品名称*/
    private String productName;

    /** 商品价格*/
    private BigDecimal productPrice;

    /** 商品数量*/
    private Integer productQuantity;

    /** 商品小图*/
    private String productIcon;


}
