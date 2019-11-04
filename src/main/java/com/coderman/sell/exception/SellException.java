package com.coderman.sell.exception;

import com.coderman.sell.myenums.ResultEnum;

/**
 * Created by zhangyukang on 2019/11/4 19:43
 */
public class SellException extends RuntimeException {
    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
    }
}
