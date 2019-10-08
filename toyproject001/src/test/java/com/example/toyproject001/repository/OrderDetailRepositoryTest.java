package com.example.toyproject001.repository;

import com.example.toyproject001.Toyproject001ApplicationTests;
import com.example.toyproject001.model.entity.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;


public class OrderDetailRepositoryTest extends Toyproject001ApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository; //저장소

   @Test
    public void create(){
        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setOrderAt(LocalDateTime.now());

        orderDetail.setUserId(4L);

        orderDetail.setItemId(1L);

        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
       Assert.assertNotNull(newOrderDetail);

    }



}
