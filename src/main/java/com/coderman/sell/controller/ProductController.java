package com.coderman.sell.controller;

import com.coderman.sell.modal.ProductCategory;
import com.coderman.sell.modal.ProductInfo;
import com.coderman.sell.service.ProductCategoryService;
import com.coderman.sell.service.ProductInfoService;
import com.coderman.sell.vo.ProductInfoVo;
import com.coderman.sell.vo.ProductVo;
import com.coderman.sell.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyukang on 2019/11/4 16:27
 */
@RestController
@RequestMapping("/sell/buyer/product")
public class ProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ResultVo list(){
        //查询所有的在架商品
        List<ProductInfo> productInfoList = productInfoService.findAllUp();
        //查询商品所有种类
        List<ProductCategory> typeList = productCategoryService.findAll();

        List<ProductVo> productVos=new ArrayList<>();
        for (ProductCategory productCategory : typeList) {
            ProductVo productVo = new ProductVo();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVo> infoVos=new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo,productInfoVo);
                    infoVos.add(productInfoVo);
                }
            }
            productVo.setFoods(infoVos);
            productVos.add(productVo);
        }
        return ResultVo.success(productVos);
    }
}
