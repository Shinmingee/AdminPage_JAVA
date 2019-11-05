package com.example.toyproject001.config;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

@Component
@EnableJpaAuditing // -> JPA에 대해서 감시를 활성화 시키겠다는 annotation
public class JpaConfig {
}
