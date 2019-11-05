package com.example.toyproject001.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  OrderGroupStatus {

    WAITING(0,"대기","주문 대기 상태"),
    COMPLETE(1,"완료","주문 완료 상태")
    ;

    private Integer id;
    private String title;
    private String description;

}
