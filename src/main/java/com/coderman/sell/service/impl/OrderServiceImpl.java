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
import com.coderman.sell.myenums.PayStatusEnum;
import com.coderman.sell.myenums.ResultEnum;
import com.coderman.sell.service.OrderService;
import com.coderman.sell.service.ProductInfoService;
import com.coderman.sell.utils.KeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
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
            //计算订单总额
            sum=productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(sum);
            //订单详情入库
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetail.setDetailId(KeyUtils.getKey());
            orderDetail.setOrderId(orderId);
            orderDetailRepository.save(orderDetail);
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(orderDetail.getProductId());
            orderItem.setProductQuantity(orderDetail.getProductQuantity());
            car.add(orderItem);
        }
        //写入订单主库
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(sum);
        orderMaster.setOrderStatus(OrderStatusEnum.CREATE_ORDER.getCode());
        orderMaster.setPayStatus(PayStatusEnum.NOT_PAID.getCode());
        OrderMaster result = orderMasterRepository.save(orderMaster);
        //扣库存
        productInfoService.decreaseStock(car);
        BeanUtils.copyProperties(result,orderDTO);
        log.info("【创建订单】 result====>{}",orderDTO);
        return orderDTO;
    }

    /**
     * 查询单个订单
     * @param orderId
     * @return
     */
    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findByOrderId(orderId);
        if(orderMaster==null){
            log.error("【查询订单】查询失败: {}",ResultEnum.ORDER_NOT_EXIST.getMessage());
            throw  new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderMaster.getOrderId());
        if(CollectionUtils.isEmpty(orderDetails)){
            log.error("【查询订单】 查询失败: {}",ResultEnum.ORDER_DETAIL_NOT_EXIST.getMessage());
            throw  new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetails);
        return orderDTO;
    }

    /**
     * 查询订单列表
     * @param buyerOpenId
     * @param pageable
     * @return
     */
    @Override
    public Page<OrderDTO> findList(String buyerOpenId, Pageable pageable) {
        List<OrderMaster> orderMasterList = orderMasterRepository.findByBuyerOpenid(buyerOpenId);
        List<OrderDTO> orderDTOList=new ArrayList<>();
        for (OrderMaster orderMaster : orderMasterList) {
            OrderDTO orderDTO = new OrderDTO();
            BeanUtils.copyProperties(orderMaster,orderDTO);
            orderDTOList.add(orderDTO);
        }

        Page<OrderDTO> page=new PageImpl<>(orderDTOList,pageable,orderDTOList.size());
        return page;
    }

    /**
     * 取消订单
     * @param orderDTO
     * @return
     */
    @Transactional
    @Override
    public OrderDTO cancle(OrderDTO orderDTO) {
        //查询订单的状态(只有新下单的订单可以取消订单)
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.CREATE_ORDER.getCode())){
            log.error("【取消订单】 取消订单失败 orderDTO={}",orderDTO);
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //改变订单的状态
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderStatus(OrderStatusEnum.CANCLE_ORDRE.getCode());
        OrderMaster result = orderMasterRepository.save(orderMaster);
        //增加商品的库存
        List<OrderItem> orderItems=new ArrayList<>();
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        if(CollectionUtils.isEmpty(orderDetailList)){
            log.error("【取订单】 取消失败 orderDTO={}",orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        for (OrderDetail orderDetail : orderDetailList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(orderDetail.getProductId());
            orderItem.setProductQuantity(orderDetail.getProductQuantity());
            orderItems.add(orderItem);
        }
        BeanUtils.copyProperties(result,orderDTO);
        //增加商品的库存信息
        productInfoService.increaseStock(orderItems);
        //用户退款

        //TODO
        return orderDTO;
    }
    /**
     * 完结订单
     * @param orderDTO
     * @return
     */
    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态(只有新创建的订单 才能完结订单)
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.CREATE_ORDER.getCode())){
            log.error("【完结订单】完结订单失败 orderDTO={}",orderDTO);
            throw  new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderStatus(OrderStatusEnum.FINISH_ORDER.getCode());
        OrderMaster result=orderMasterRepository.save(orderMaster);
        //改变用户订单状态
        BeanUtils.copyProperties(result,orderDTO);
        return orderDTO;
    }

    /**
     * 订单支付
     * @param orderDTO
     * @return
     */
    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态(完结订单之后才可以支付)
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.FINISH_ORDER.getCode())){
            log.error("【订单支付】 支付失败 orderDTO={}",orderDTO);
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if(!orderDTO.getPayStatus().equals(PayStatusEnum.NOT_PAID.getCode())){
            log.error("【订单支付】 支付失败 orderDTO={}",orderDTO);
            throw new SellException(ResultEnum.PAY_STATUS_ERROR);
        }
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setPayStatus(PayStatusEnum.PAID.getCode());
        OrderMaster result=orderMasterRepository.save(orderMaster);
        //改变支付状态
        BeanUtils.copyProperties(result,orderDTO);
        //支付
        //TODO
        return orderDTO;
    }
}
