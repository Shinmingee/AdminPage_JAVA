package com.example.toyproject001.repository;

import com.example.toyproject001.Toyproject001ApplicationTests;
import com.example.toyproject001.model.entity.OrderDetail;
import com.example.toyproject001.model.enumclass.OrderDetailStatus;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class OrderDetailRepositoryTest extends Toyproject001ApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository; //저장소

   @Test
    public void create(){
        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setStatus(OrderDetailStatus.WAITING);
        orderDetail.setArrivalDate(LocalDateTime.now().plusDays(2));
        orderDetail.setCreatedAt(LocalDateTime.now());
        orderDetail.setCreatedBy("Admin Server");

        //NotNull은 아니지만, Test해보기 위해 값 입력
        orderDetail.setQuantity(1);
        orderDetail.setTotalPrice(BigDecimal.valueOf(1500000));

       //orderDetail.setOrderGroupId(1L); //어떤 장바구니에 Long-> OrderGroup
       //orderDetail.setItemId(1L); //어떠한 상품들이 있는지 Long-> Item


       OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
       Assert.assertNotNull(newOrderDetail);

    }


}
