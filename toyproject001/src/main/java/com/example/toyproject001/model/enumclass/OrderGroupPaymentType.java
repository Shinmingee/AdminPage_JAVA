package com.example.toyproject001.model.enumclass;

//납부 방법 : 카드(CARD),현금(CASH), 계좌이체(ACCOUNT), 포인트(POINT)

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  OrderGroupPaymentType {

    CARD(0,"카드","카드납부"),
    CASH(1,"현금", "현금납부"),
    BANK_TRANSFER(2,"계좌이체","계좌이체납부"),
    POINT(3, "포인트결제","포인트결제납부")
    ;

    private Integer id;
    private String title;
    private String description;
}
