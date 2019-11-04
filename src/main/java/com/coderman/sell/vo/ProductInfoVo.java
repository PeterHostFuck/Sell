package com.coderman.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by zhangyukang on 2019/11/4 16:36
 */
@Data
public class ProductInfoVo {
    @JsonProperty(value = "id")
    private String productId;

    @JsonProperty(value = "price")
    private BigDecimal productPrice;

    @JsonProperty(value = "description")
    private String productDescription;

    @JsonProperty(value = "icon")
    private String productIcon;
}
