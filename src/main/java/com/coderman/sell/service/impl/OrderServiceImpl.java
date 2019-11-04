package com.coderman.sell.service.impl;

import com.coderman.sell.dao.OrderDetailRepository;
import com.coderman.sell.dao.OrderMasterRepository;
import com.coderman.sell.dao.ProductInfoRepository;
import com.coderman.sell.dto.OrderDTO;
import com.coderman.sell.dto.OrderItem;
import com.coderman.sell.exception.SellException;
import com.coderman.sell.modal.OrderDetail;
import com.coderman.sell.modal.OrderMaster;
import com.coderman.sell.modal.ProductInfo;
import com.coderman.sell.myenums.OrderStatusEnum;
import com.coderman.sell.myenums.ResultEnum;
import com.coderman.sell.service.OrderService;
import com.coderman.sell.service.ProductInfoService;
import com.coderman.sell.utils.KeyUtils;
import com.sun.org.apache.regexp.internal.RE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyukang on 2019/11/4 19:33
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Transactional
    @Override
    public OrderDTO create(OrderDTO orderDTO) {

        BigDecimal sum=new BigDecimal(0);
        String orderId=KeyUtils.getKey();
        List<OrderItem> car=new ArrayList<>();
        //查询商品的数量，价格
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        for (OrderDetail orderDetail : orderDetailList) {
            String productId = orderDetail.getProductId();
            ProductInfo productInfo = productInfoRepository.findByProductId(productId);
            if(productInfo==null){
                throw  new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //计算总额
            sum=productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(sum);
            //单项详情入库
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetail.setDetailId(KeyUtils.getKey());
            orderDetail.setOrderId(orderId);
            orderDetailRepository.save(orderDetail);
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(orderDetail.getProductId());
            orderItem.setProductQuantity(orderDetail.getProductQuantity());
            car.add(orderItem);
        }
        //写入订单数据
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(sum);
        orderMaster.setOrderStatus(OrderStatusEnum.CREATE_ORDER.getCode());
        orderMaster.setPayStatus(OrderStatusEnum.NOT_PAID.getCode());
        orderMasterRepository.save(orderMaster);
        //扣库存
        productInfoService.decreaseStock(car);
        log.info("【创建订单】 result====>{}",orderDTO);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findByOrderId(orderId);
        if(orderMaster==null){
            throw  new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderMaster.getOrderId());
        if(CollectionUtils.isEmpty(orderDetails)){
            throw  new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetails);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenId, Pageable pageable) {

        return null;
    }

    @Override
    public OrderDTO cancle(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
