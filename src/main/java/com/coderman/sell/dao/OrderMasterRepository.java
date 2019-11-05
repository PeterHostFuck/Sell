package com.coderman.sell.dao;

import com.coderman.sell.modal.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by zhangyukang on 2019/11/4 19:16
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {

    OrderMaster findByOrderId(String orderId);

    List<OrderMaster> findByBuyerOpenid(String buyerOpenId);
}
