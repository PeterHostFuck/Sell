package com.coderman.sell.myenums;

import lombok.Getter;

/**
 * Created by zhangyukang on 2019/11/4 19:44
 */
@Getter
public enum  ResultEnum {
    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11, "商品库存错误"),
    ORDER_NOT_EXIST(12,"订单不存在"),
    ORDER_DETAIL_NOT_EXIST(13,"订单详情不存在" ),
    ORDER_STATUS_ERROR(14,"订单的状态不正确"),
    PAY_STATUS_ERROR(15,"支付状态错误" );

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;

}
