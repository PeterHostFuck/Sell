package com.coderman.sell.modal;

import com.coderman.sell.myenums.OrderStatusEnum;
import com.coderman.sell.myenums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单主表
 * Created by zhangyukang on 2019/11/4 17:18
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {
    /*订单主键*/
    @Id
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

    /**订单状态: 默认的订单状态是新订单*/
    private Integer orderStatus= OrderStatusEnum.CREATE_ORDER.getCode();

    /**支付状态：默认订单的支付状态是未支付*/
    private Integer payStatus= PayStatusEnum.NOT_PAID.getCode();

    private Date createTime;

    private Date updateTime;


}
