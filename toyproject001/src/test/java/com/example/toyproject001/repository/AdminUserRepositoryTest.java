package com.example.toyproject001.repository;

import com.example.toyproject001.Toyproject001ApplicationTests;
import com.example.toyproject001.model.entity.AdminUser;
import com.example.toyproject001.model.enumclass.AdminUserRole;
import com.example.toyproject001.model.enumclass.AdminUserStatus;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class AdminUserRepositoryTest extends Toyproject001ApplicationTests {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Test
    public void create(){

        String account = "AdminUser01";
        String password = "AdminUser01";
        AdminUserStatus status = AdminUserStatus.REGISTERED;
        AdminUserRole role = AdminUserRole.PARTNER;
//        LocalDateTime createdAt = LocalDateTime.now();
//        String createdBy = "Admin Server";

        AdminUser adminUser = new AdminUser();

        adminUser.setAccount(account);
        adminUser.setPassword(password);
        adminUser.setStatus(status);
        adminUser.setRole(role);
//        adminUser.setCreatedAt(createdAt);
//        adminUser.setCreatedBy(createdBy);

        AdminUser newAdminUser = adminUserRepository.save(adminUser);
        Assert.assertNotNull(adminUser);

        newAdminUser.setAccount("CHANGE");
        adminUserRepository.save(newAdminUser);
//        Assert.assertEquals(newAdminUser.getAccount(),account);
//        Assert.assertEquals(newAdminUser.getPassword(),password);
    }

    @Test
    public void read(){

        String account = "AdminUser01";
        Optional<AdminUser> optionalAdminUser = adminUserRepository.findByAccount(account);

        optionalAdminUser.ifPresent(c->{
            Assert.assertEquals(c.getAccount(), account);

            System.out.println(c.getId());
            System.out.println(c.getPassword());
            System.out.println(c.getAccount());
            System.out.println(c.getStatus());
            System.out.println(c.getRole());
            System.out.println(c.getCreatedAt());
            System.out.println(c.getCreatedBy());

        });



    }

    public void update(){

    }

    public void delete(){

    }
}
