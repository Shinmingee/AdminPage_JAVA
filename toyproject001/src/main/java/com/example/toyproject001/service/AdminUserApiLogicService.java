package com.example.toyproject001.service;

import com.example.toyproject001.controller.ifs.CrudInterface;
import com.example.toyproject001.model.entity.AdminUser;
import com.example.toyproject001.model.network.Header;
import com.example.toyproject001.model.network.request.AdminUserApiRequest;
import com.example.toyproject001.model.network.response.AdminUserApiResponse;
import com.example.toyproject001.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AdminUserApiLogicService implements CrudInterface<AdminUserApiRequest, AdminUserApiResponse> {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Override
    public Header<AdminUserApiResponse> create(Header<AdminUserApiRequest> request) {

        AdminUserApiRequest body = request.getData();

        AdminUser adminUser = AdminUser.builder()
                .account(body.getAccount())
                .password(body.getPassword())
                .status(body.getStatus())
                .role(body.getRole())
                .lastLoginAt(LocalDateTime.now())
                .passwordUpdatedAt(LocalDateTime.now())
                .loginFailCount(body.getLoginFailCont())
                .registeredAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .createdBy(body.getCreatedBy())
                .build();
        AdminUser newAdminUser = adminUserRepository.save(adminUser);

        return response(newAdminUser);
    }

    @Override
    public Header<AdminUserApiResponse> read(Long id) {

        Optional<AdminUser> optional = adminUserRepository.findById(id);

        return optional.map(adminUser -> response(adminUser))
                .orElseGet(()->Header.ERROR("데이터 없음!!"));

    }

    @Override
    public Header<AdminUserApiResponse> update(Header<AdminUserApiRequest> request) {

        AdminUserApiRequest body = request.getData();

        Optional<AdminUser> optional = adminUserRepository.findById(body.getId());

        return optional.map(adminUser -> {
            adminUser.setAccount(body.getAccount())
                    .setRole(body.getRole())
                    .setStatus(body.getStatus())
                    .setPassword(body.getPassword())
                    .setCreatedBy(body.getCreatedBy())
                    .setCreatedAt(body.getCreatedAt())
                    .setUpdatedAt(body.getUpdatedAt())
                    .setUpdatedBy(body.getUpdatedBy())
                    .setLastLoginAt(body.getLastLoginAt())
                    .setLoginFailCount(body.getLoginFailCont())
                    .setPasswordUpdatedAt(body.getPasswordUpdatedAt())
                    .setRegisteredAt(body.getRegisteredAt())
                    .setUnregisteredAt(body.getUnregisteredAt());
            return adminUser;
        })
                .map(adminUser-> adminUserRepository.save(adminUser))
                .map(updateAdminUser -> response(updateAdminUser))
                .orElseGet(()->Header.ERROR("데이터 없음!!"));
    }

    @Override
    public Header delete(Long id) {

        Optional<AdminUser> optional = adminUserRepository.findById(id);

        return optional.map(adminUser -> {
            adminUserRepository.delete(adminUser);
            return Header.OK();
        })
                .orElseGet(()->Header.ERROR("데이터 없음!"));

    }

    public Header<AdminUserApiResponse> response(AdminUser adminUser){

        AdminUserApiResponse body = AdminUserApiResponse.builder()
                .id(adminUser.getId())
                .account(adminUser.getAccount())
                .password(adminUser.getPassword())
                .status(adminUser.getStatus())
                .role(adminUser.getRole())
                .loginFailCont(adminUser.getLoginFailCount())
                .lastLoginAt(adminUser.getLastLoginAt())
                .createdAt(adminUser.getCreatedAt())
                .createdBy(adminUser.getCreatedBy())
                .updatedAt(adminUser.getUpdatedAt())
                .updatedBy(adminUser.getUpdatedBy())
                .registeredAt(adminUser.getRegisteredAt())
                .unregisteredAt(adminUser.getUnregisteredAt())
                .passwordUpdatedAt(adminUser.getPasswordUpdatedAt())
                .build();

        return Header.OK(body);
    }

}
