package com.example.toyproject001.model.network.request;

import com.example.toyproject001.model.enumclass.OrderGroupOrderType;
import com.example.toyproject001.model.enumclass.OrderGroupPaymentType;
import com.example.toyproject001.model.enumclass.OrderGroupStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderGroupApiRequest {

    private Long id;

    private OrderGroupStatus status;

    private OrderGroupOrderType orderType;

    private String revAddress;

    private String revName;

    private OrderGroupPaymentType paymentType;

    private BigDecimal totalPrice;

    private Integer totalQuantity;

    private LocalDateTime orderAt;

    private LocalDateTime arrivalDate;

    private Long userId;



}
