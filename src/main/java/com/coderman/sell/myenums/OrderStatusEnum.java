package com.coderman.sell.myenums;

import lombok.Getter;

/**
 * Created by zhangyukang on 2019/11/4 20:17
 */
@Getter
public enum  OrderStatusEnum {
    CREATE_ORDER(0,"创建订单"),

    CANCLE_ORDRE(1,"取消订单"),

    FINISH_ORDER(2,"完结订单");
    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
