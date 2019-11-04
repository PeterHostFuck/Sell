package com.coderman.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by zhangyukang on 2019/11/4 16:34
 */
@Data
public class ProductVo {

    @JsonProperty(value = "name")
    private  String categoryName;

    @JsonProperty(value = "type")
    private Integer categoryType;

    private List<ProductInfoVo> foods;


}
