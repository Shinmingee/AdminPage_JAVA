package com.example.toyproject001.repository;

import com.example.toyproject001.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //JPA 의 Query Method
    Optional<User> findByAccountAndEmail(String account, String email);

    User findFirstByPhoneNumberOrderByIdDesc(String phoneNumber);

}
//    //select * form user where id=? and email = ?
//

