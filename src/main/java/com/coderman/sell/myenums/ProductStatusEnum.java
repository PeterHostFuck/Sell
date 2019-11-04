package com.coderman.sell.myenums;

import lombok.Getter;

/**
 * 商品在架的状态
 * Created by zhangyukang on 2019/11/4 16:11
 */
@Getter
public enum  ProductStatusEnum {
    UP(0,"在架")
    ,DOWN(1,"下架");


    private Integer code;
    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
