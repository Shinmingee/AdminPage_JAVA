package com.example.toyproject001.model.enumclass;

//주문 타입 : 일괄(ALL), 개별(Individual)

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.criteria.CriteriaBuilder;

@Getter
@AllArgsConstructor
public enum  OrderGroupOrderType {

    ALL(0, "일괄", "일괄주문"),
    EACH(1, "개별", "개별주문")
    ;

    private Integer id;
    private String title;
    private String description;
}
