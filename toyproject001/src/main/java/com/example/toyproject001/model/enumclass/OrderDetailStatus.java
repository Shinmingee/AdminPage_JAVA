package com.example.toyproject001.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 주문상태 : 대기(WAITING), 완료(COMPLETE)

@Getter
@AllArgsConstructor
public enum OrderDetailStatus {

    CONFIRM(0,"주문 확인", "주문 확인 상태"),
    ORDERING(1,"주문진행중","주문 진행 상태"),
    COMPLETE(2,"완료","주문 완료 상태")
    ;

    private Integer id;
    private String title;
    private String description;

}
