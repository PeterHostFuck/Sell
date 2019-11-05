package com.coderman.sell.test;

import com.coderman.sell.dto.OrderDTO;
import com.coderman.sell.modal.OrderDetail;
import com.coderman.sell.service.impl.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    /**
     * 测试订单的支付
     */
    @Test
    public void testpaid(){
        OrderDTO orderDTO=orderService.findOne("FC685792-BFC5-42931572922759067");
        OrderDTO finish = orderService.paid(orderDTO);
        System.out.println(finish);
    }
    /**
     * 测试订单的完结
     */
    @Test
    public void testfinish(){
        OrderDTO orderDTO=orderService.findOne("FC685792-BFC5-42931572922759067");
        OrderDTO result=orderService.finish(orderDTO);
        System.out.println(result);
    }
    /**
     * 测试取消订单
     */
    @Test
    public void testCancleOrder(){
         OrderDTO orderDTO=orderService.findOne("FC685792-BFC5-42931572922759067");
        System.out.println(orderDTO);
        OrderDTO cancle = orderService.cancle(orderDTO);
        System.out.println(cancle);
    }
    /**
     * 查询用户的订单列表
     */
    @Test
    public void testfindList(){
        PageRequest pageRequest=PageRequest.of(0,1);
        Page<OrderDTO> orderDTOS = orderService.findList("FADRFJFASDFAS", pageRequest);
        System.out.println(orderDTOS);

    }
    @Test
    public void testFindOne(){
        OrderDTO one = orderService.findOne("D9E67B97-A1F9-43071572920729036");
        log.info("【查询订单】 result={}",one);
    }

    @Test
    public void testCreateOrder(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress("江西");
        orderDTO.setBuyerName("章宇康");
        orderDTO.setBuyerOpenid("FADRFJFASDFAS");
        orderDTO.setBuyerPhone("15079437282");
        List<OrderDetail> orderDetails=new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("94348");
        o1.setProductQuantity(1);
        orderDetails.add(o1);

        orderDTO.setOrderDetailList(orderDetails);
        OrderDTO orderDTO1 = orderService.create(orderDTO);
        System.out.println(orderDTO1);
    }

}
