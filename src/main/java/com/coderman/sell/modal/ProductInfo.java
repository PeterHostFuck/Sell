package com.coderman.sell.modal;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 商品详情
 * Created by zhangyukang on 2019/11/4 11:19
 */
@Data
@Entity
@DynamicUpdate
public class ProductInfo {

    @Id
   private String productId;
    /**商品的名字*/
   private String productName;
    /***商品的价格*/
   private BigDecimal productPrice;
    /**库存*/
   private Integer productStock;
    /**描述*/
   private String productDescription;
    /*商品小图*/
   private String productIcon;
    /*商品的类型*/
   private Integer categoryType;
    /*商品的上架状态*/
   private Integer productStatus;




}
