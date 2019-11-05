package com.example.toyproject001.model.enumclass;

// 회원권한: 파트너(PARTNER), 사용자(USER), 관리자(ADMINISTER)

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdminUserRole {

    PARTNER(0, "파트너","파트너 회원"),
    USER(1,"사용자", "사용자 회원"),
    ADMINISTER(2, "관리자", "관리자 회원")
    ;

    private Integer id;
    private String title;
    private String description;
}
