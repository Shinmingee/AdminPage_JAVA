package com.example.toyproject001.model.network.response;

import com.example.toyproject001.model.enumclass.OrderGroupOrderType;
import com.example.toyproject001.model.enumclass.OrderGroupPaymentType;
import com.example.toyproject001.model.enumclass.OrderGroupStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderGroupApiResponse {

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

    private List<ItemApiResponse> itemApiResponseList;



}
