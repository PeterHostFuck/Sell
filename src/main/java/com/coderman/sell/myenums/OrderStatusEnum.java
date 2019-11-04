package com.coderman.sell.myenums;

import lombok.Getter;

/**
 * Created by zhangyukang on 2019/11/4 20:17
 */
@Getter
public enum  OrderStatusEnum {
    CREATE_ORDER(0,"创建订单"),
    NOT_PAID(0,"未支付");
    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
