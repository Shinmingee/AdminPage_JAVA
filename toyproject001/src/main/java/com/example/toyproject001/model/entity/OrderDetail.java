package com.example.toyproject001.model.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@ToString(exclude = {"user", "item"})
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private LocalDateTime arrivalDate;

    private Integer quantity;

    private Integer totalPrice;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

}
    //private LocalDateTime orderAt;
//    //N:1
//    @ManyToOne
//    private User user; //hibernate에서 상관관계 정리
//
//    @ManyToOne
//    private Item item;

