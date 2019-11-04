package com.coderman.sell.dto;

import com.coderman.sell.modal.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangyukang on 2019/11/4 19:19
 */
@Data
public class OrderDTO {

    private String orderId;

    /**买家名字*/
    private String buyerName;

    /**买家电话*/
    private String buyerPhone;

    /**买家地址**/
    private String buyerAddress;

    /**买家微信的openid*/
    private String buyerOpenid;

    /**总金额*/
    private BigDecimal orderAmount;

    /**订单状态*/
    private Integer orderStatus;

    /**支付状态*/
    private Integer payStatus;

    private Date createTime;

    private Date updateTime;

    /** 订单详情 */
    private List<OrderDetail> orderDetailList;


}
