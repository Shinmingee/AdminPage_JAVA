package com.example.toyproject001.service;

import com.example.toyproject001.controller.ifs.CrudInterface;
import com.example.toyproject001.model.entity.User;
import com.example.toyproject001.model.enumclass.UserStatus;
import com.example.toyproject001.model.network.Header;
import com.example.toyproject001.model.network.request.UserApiRequest;
import com.example.toyproject001.model.network.response.UserApiResponse;
import com.example.toyproject001.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserRepository userRepository;

    //1. request data 가져오기

    //2. user 생성

    //3. 생성된 데이터 기준으로 UserApiResponse 만들어서 보내주면 됨


   @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

       //1. request data 불러오기
       UserApiRequest userApiRequest = request.getData();

       //2. User 생성
       User user = User.builder()
               .account(userApiRequest.getAccount())
               .password(userApiRequest.getPassword())
               .status(UserStatus.REGISTERED) //enum으로 하는 법 있음
               .phoneNumber(userApiRequest.getPhoneNumber())
               .email(userApiRequest.getEmail())
               .registeredAt(LocalDateTime.now())
               .build();
       User newUser = userRepository.save(user);

       //3. 생성된 데이터 -> userApiResponse return
       return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        //id -> repository getOne, getById
        Optional<User> optional = userRepository.findById(id);

        // user->userApiResponse
        return optional
                .map(user ->response(user)) //map으로 optional에서 데이터 가져옴.
                .orElseGet(
                        ()->Header.ERROR("데이터 없음")
                ); //데이터가 없을때

        /*
        람다형식으로 표현>>
        return userRepository.findById(id)
                .map(user->response(user))
                .orElseGet(()->Header.ERROR("데이터 없음"));
        */
    }
    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        //1. request data 가져와
        UserApiRequest userApiRequest = request.getData();

        //2. 가져온 data의 id를 체크
        Optional<User> optional = userRepository.findById(userApiRequest.getId());

        //3. update
        //4. userApiResponse
        return optional.map(user->{
            user.setAccount(userApiRequest.getAccount())
                .setPassword(userApiRequest.getPassword())
                .setStatus(userApiRequest.getStatus())
                .setPhoneNumber(userApiRequest.getPhoneNumber())
                .setEmail(userApiRequest.getEmail())
                .setRegisteredAt(userApiRequest.getRegisteredAt())
                .setUnregisteredAt(userApiRequest.getUnregisteredAt());
            return user;
        })
                .map(user->userRepository.save(user)) //update ->newUser
                .map(updateUser -> response(updateUser)) //userApiResponse
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
       //1.id -> repository -> user
       Optional<User> optional = userRepository.findById(id);

       //2. repository -> delete
       return optional.map(user -> {
           userRepository.delete(user);
           return Header.OK();
       }).orElseGet(()->Header.ERROR("데이터 없음"));
    }

    //다른 부분에서도 사용할 수 있기 때문에 따로 빼움
    // 앞단에 뿌려주기 위한 response => Entity에 저장된 데이터를 ApiResponse로
    private Header<UserApiResponse> response(User user){
       //user -> userApiResponse

        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword()) //Todo: 암호화, 길이
                .status(user.getStatus())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        //Header + data return

        return Header.OK(userApiResponse);

    }

}
