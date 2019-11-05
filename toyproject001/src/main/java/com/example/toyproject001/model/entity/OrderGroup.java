package com.example.toyproject001.model.entity;

import com.example.toyproject001.model.enumclass.OrderGroupOrderType;
import com.example.toyproject001.model.enumclass.OrderGroupPaymentType;
import com.example.toyproject001.model.enumclass.OrderGroupStatus;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"user"})
@EntityListeners(AuditingEntityListener.class)
@Builder
@Accessors(chain = true)
public class OrderGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderGroupStatus status; //주문상태 : 대기(WAITING), 완료(COMPLETE)

    @Enumerated(EnumType.STRING)
    private OrderGroupOrderType orderType; //주문 타입 : 일괄(ALL), 개별(Individual)

    private String revAddress;

    private String revName;

    @Enumerated(EnumType.STRING)
    private OrderGroupPaymentType paymentType; //납부 방법 : 카드(CARD),현금(CASH), 계좌이체(ACCOUNT), 포인트(POINT)

    private BigDecimal totalPrice;

    private Integer totalQuantity;

    private LocalDateTime orderAt;

    private LocalDateTime arrivalDate;

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedBy;

    //OrderGroup N :1 User
    @ManyToOne
    private User user;

    //OrderGroup 1 : N OrderDetail
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderGroup")
    private List<OrderDetail> orderDetailList;
}
