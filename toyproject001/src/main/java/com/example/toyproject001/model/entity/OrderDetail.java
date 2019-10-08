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
@ToString(exclude = {"user", "item"})
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //N:1
    @ManyToOne
    private User user; //hibernate에서 상관관계 정리

    @ManyToOne
    private Item item;

    private LocalDateTime orderAt;



}
