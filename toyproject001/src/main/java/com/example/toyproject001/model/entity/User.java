package com.example.toyproject001.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor //매개변수를 포함하는 생성자
@NoArgsConstructor
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account;

    private String email;

    private String phoneNumber;

    @Column(name = "create_at")
    private LocalDateTime createdAt;

    @Column(name = "create_by")
    private String cratedBy;

    @Column(name = "update_at")
    private LocalDateTime updatedAt;

    @Column(name = "update_by")
    private String updatedBy;

}
