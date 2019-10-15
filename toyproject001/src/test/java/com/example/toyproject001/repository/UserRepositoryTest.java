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
        String account = "Test01";
        String password = "Test01";
        String status = "REGISTERED";
        String email = "TEST01@test.com";
        String phoneNumber = "010-1111-2222";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        User user = new User();

        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setPhoneNumber(phoneNumber);
        user.setEmail(email);
        user.setRegisteredAt(registeredAt);
        user.setCreatedAt(createdAt);
        user.setCreatedBy(createdBy);

        //객체 생성시, @Builder
        User u = User.builder()
                .account(account)
                .password(password)
                .email(email)
                .status(status)
                .build();

        User newUser = userRepository.save(user);

        Assert.assertNotNull(newUser);
        Assert.assertEquals(newUser.getAccount(), account);
    }

    @Test
    @Transactional
    public void read(){

        //String account = "Test01";
        //String email = "TEST01@test.com";
        //Optional<User> optionalUser = userRepository.findByAccountAndEmail(account, email);

        String phoneNumber = "010-1111-2222";

        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc(phoneNumber);

        //update user -> @Accessors(chain = true)
        user
                .setEmail("")
                .setPhoneNumber("")
                .setStatus("");

        User u = new User().setAccount("").setEmail("").setPassword("");

        if(user != null){
            user.getOrderGroupList().stream().forEach(orderGroup -> {
                System.out.println("-----------------주문묶음-------------------");
                System.out.println("수령인 :" + orderGroup.getRevName());
                System.out.println("수령지 : " + orderGroup.getRevAddress());
                System.out.println("주문 총 수량 : " + orderGroup.getTotalQuantity());
                System.out.println("주문 총 금액 : " +orderGroup.getTotalPrice());
                System.out.println("주문 상태 : "+ orderGroup.getStatus());

                System.out.println("-----------------주문상세------------------");
                orderGroup.getOrderDetailList().stream().forEach(orderDetail -> {
                    System.out.println("상품 카테고리 : "+ orderDetail.getItem().getPartner().getCategory().getType());
                    System.out.println("상품 이름 : " +  orderDetail.getItem().getName());
                    System.out.println("상품 브랜드 : " + orderDetail.getItem().getBrandName());
                    System.out.println("고객 센터 번호 : "+ orderDetail.getItem().getPartner().getCallCenter()) ;
                    System.out.println("도착 일자 : " + orderDetail.getArrivalDate());
                    System.out.println("주문 상태 : " +orderDetail.getStatus());

                });

            });

        }

        Assert.assertNotNull(user);

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

