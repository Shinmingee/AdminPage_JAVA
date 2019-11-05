package com.example.toyproject001.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 주문상태 : 대기(WAITING), 완료(COMPLETE)

@Getter
@AllArgsConstructor
public enum OrderDetailStatus {
    WAITING(0, "대기", "주문 대기상태"),
    COMPLETE(1,"완료", "주문 완료상태")
    ;

    private Integer id;
    private String title;
    private String description;

}
