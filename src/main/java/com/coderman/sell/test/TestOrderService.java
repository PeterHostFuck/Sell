package com.coderman.sell.test;

import com.coderman.sell.dto.OrderDTO;
import com.coderman.sell.modal.OrderDetail;
import com.coderman.sell.service.impl.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyukang on 2019/11/4 20:02
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestOrderService {

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void testFindOne(){
        OrderDTO one = orderService.findOne("15728711401562a3878");
        log.info("【查询订单】 result={}",one);
    }

    @Test
    public void testCreateOrder(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress("江西");
        orderDTO.setBuyerName("zhangyukang");
        orderDTO.setBuyerOpenid("FADRFJFASDFAS");
        orderDTO.setBuyerPhone("15079437282");
        List<OrderDetail> orderDetails=new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("94348");
        o1.setProductQuantity(9);
        orderDetails.add(o1);

        orderDTO.setOrderDetailList(orderDetails);
        OrderDTO orderDTO1 = orderService.create(orderDTO);
    }

}
