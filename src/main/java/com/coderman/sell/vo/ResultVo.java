package com.coderman.sell.vo;

import lombok.Data;

/**
 * Created by zhangyukang on 2019/11/4 16:29
 */
@Data
public class ResultVo {
    private Integer code;
    private String msg;
    private Object data;

    public  static ResultVo success(){
        return new ResultVo(0,"成功");
    }

    public  static ResultVo success(Object object){
        return new ResultVo(0,"成功",object);
    }

    public  static ResultVo error(){
        return new ResultVo(1,"失败");
    }

    public ResultVo() {
    }

    public ResultVo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultVo(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
