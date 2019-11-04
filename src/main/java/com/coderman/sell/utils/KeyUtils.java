package com.coderman.sell.utils;

import java.util.UUID;

/**
 * 生成数据库的键
 * Created by zhangyukang on 2019/11/4 19:52
 */
public class KeyUtils {

    /**
     * 生成唯一key
     * @return
     */
    public static synchronized String getKey(){
        return  UUID.randomUUID().toString().substring(0,18).toUpperCase()+System.currentTimeMillis();
    }

}
