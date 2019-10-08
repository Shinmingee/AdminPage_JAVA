package com.example.toyproject001.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor //생성자
@NoArgsConstructor //모든 매개변수를 포함하는 생성자
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account;

    private String password;

    private String status;

    private String email;

    private String phoneNumber;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredBy;

    private LocalDateTime createdAt;

    private String cratedBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    //fetch type
    // LAZY : 지연로딩 - 따로 메소드를 호출하지 않는 이상(get method),
    //        연관관계가 있는 테이블에서 select를 하지 않겠다.
    // EAGER : 즉시로딩 - 연관관계가 설정되어있는 모든 table에게서 join을 걸어 즉시 로딩한다.
    //        table 의 관계가 (1:1)일때 주로 쓰인다.

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
//    private List<OrderDetail> orderDetailList;
}
