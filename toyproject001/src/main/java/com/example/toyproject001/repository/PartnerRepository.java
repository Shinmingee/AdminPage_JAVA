package com.example.toyproject001.repository;

import com.example.toyproject001.model.entity.Category;
import com.example.toyproject001.model.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//JpaRepository 상속을 받을 때, <Entity class, ID 형>를 받아야 한다.

public interface PartnerRepository extends JpaRepository<Partner, Long> {
    List<Partner> findByCategory(Category category);
}
