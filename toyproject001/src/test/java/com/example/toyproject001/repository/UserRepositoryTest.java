package com.example.toyproject001.repository;

import com.example.toyproject001.Toyproject001ApplicationTests;
import com.example.toyproject001.model.entity.Item;
import com.example.toyproject001.model.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends Toyproject001ApplicationTests {

    //DI
    @Autowired
    private UserRepository userRepository; //얘는 한번만 생성 (Singleton)

    @Test
    public void crate(){
        //String sql = insert int user (%s, %s, %d) value (account, email, age);
        User user = new User();
        user.setAccount("TestUser001");
        user.setEmail("TestUser03@test.com");
        user.setPhoneNumber("010-1111-3333");
        user.setCreatedAt(LocalDateTime.now());
        user.setCratedBy("TestUser001");

        User newUser = userRepository.save(user);

        System.out.println("newUser : " + newUser);

    }

    @Test
    @Transactional
    public void read(){
        Optional<User> user = userRepository.findByAccountAndEmail("TestUser001", "TestUser001@test.com");

        user.ifPresent(selectUser ->{

            selectUser.getOrderDetailList().stream().forEach(detail->{
                    Item item = detail.getItem();
                    System.out.println(item);
            });
        });
    }

    @Test
    public void update(){

        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser -> {
            selectUser.setAccount("PPPP");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });
    }

    @Test
    @Transactional //rollback해준다.
    public void delete(){
        Optional<User> user = userRepository.findById(3L);

        Assert.assertTrue(user.isPresent()); //true

        user.ifPresent(selectUser->{
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(3L);

        Assert.assertFalse(deleteUser.isPresent());

//        if(deleteUser.isPresent()){
//            System.out.println("데이터 존재: " + deleteUser.get());
//        }else{
//            System.out.println("데이터 삭제 데이터 없음");
//        }
    }
}

