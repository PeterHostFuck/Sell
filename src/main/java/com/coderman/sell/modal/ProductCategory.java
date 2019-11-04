package com.coderman.sell.modal;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 商品类目
 * Created by zhangyukang on 2019/11/4 10:36
 */
@Data
@Entity
@DynamicUpdate
public class ProductCategory {
    /**商品类型的主键*/
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer categoryId;
    /**商品类型名字。*/
    private String categoryName;
    /**商类型的id.*/
    private Integer categoryType;

    public ProductCategory(Integer categoryId,String categoryName,Integer categoryType){
        this.categoryId=categoryId;
        this.categoryName=categoryName;
        this.categoryType=categoryType;
    }

    public ProductCategory() {
    }
}
