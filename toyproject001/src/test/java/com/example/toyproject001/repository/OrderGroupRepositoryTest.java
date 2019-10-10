package com.example.toyproject001.repository;

import com.example.toyproject001.Toyproject001ApplicationTests;
import com.example.toyproject001.model.entity.OrderGroup;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrderGroupRepositoryTest extends Toyproject001ApplicationTests {

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Test
    public void create(){

        OrderGroup orderGroup = new OrderGroup();

        orderGroup.setStatus("COMPLETE");
        orderGroup.setOrderType("ALL");
        orderGroup.setRevAddress("서울시 마포구 독박로12");
        orderGroup.setPaymentType("CARD");
        orderGroup.setTotalPrice(BigDecimal.valueOf(1500000));
        orderGroup.setTotalQuantity(1);
        orderGroup.setOrderAt(LocalDateTime.now().minusDays(2));
        orderGroup.setArrivalDate(LocalDateTime.now());

        orderGroup.setCreatedAt(LocalDateTime.now());
        orderGroup.setCreatedBy("Admin Server");

        //orderGroup.setUserId(2L); Long -> User


        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);
        Assert.assertNotNull(newOrderGroup);
    }

    public void read(){

    }

    public void update(){

    }

    public void delete(){

    }
}
