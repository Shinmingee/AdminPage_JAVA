package com.example.toyproject001.service;

import com.example.toyproject001.controller.ifs.CrudInterface;
import com.example.toyproject001.model.entity.OrderGroup;
import com.example.toyproject001.model.network.Header;
import com.example.toyproject001.model.network.request.OrderGroupApiRequest;
import com.example.toyproject001.model.network.response.OrderGroupApiResponse;
import com.example.toyproject001.repository.OrderGroupRepository;
import com.example.toyproject001.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrderGroupApiLogicService implements CrudInterface<OrderGroupApiRequest, OrderGroupApiResponse> {

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Header<OrderGroupApiResponse> create(Header<OrderGroupApiRequest> request) {
        //request에서 데이터 가져온다.
        OrderGroupApiRequest orderGroupApiRequest = request.getData();

        //entity에 저장->레파지토리
        OrderGroup orderGroup = OrderGroup.builder()
                .status(orderGroupApiRequest.getStatus())
                .orderType(orderGroupApiRequest.getOrderType())
                .revAddress(orderGroupApiRequest.getRevAddress())
                .revName(orderGroupApiRequest.getRevName())
                .paymentType(orderGroupApiRequest.getPaymentType())
                .totalPrice(orderGroupApiRequest.getTotalPrice())
                .totalQuantity(orderGroupApiRequest.getTotalQuantity())
                .orderAt(LocalDateTime.now())
                .user(userRepository.getOne(orderGroupApiRequest.getUserId()))
                .build();

        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);
        return response(newOrderGroup);
    }

    @Override
    public Header<OrderGroupApiResponse> read(Long id) {
        //id로 저장되어 있는 데이터 불러오기
        Optional<OrderGroup> optional = orderGroupRepository.findById(id);

        return optional.map(orderGroup -> response(orderGroup))
                .orElseGet(()->Header.ERROR("데이터 없음"));

    }

    @Override
    public Header<OrderGroupApiResponse> update(Header<OrderGroupApiRequest> request) {
        //request의 데이터 가져오기
        OrderGroupApiRequest orderGroupApiRequest = request.getData();

        //id 확인하기
        Optional <OrderGroup> optional = orderGroupRepository.findById(orderGroupApiRequest.getId());

        //set
        return optional.map(orderGroup->{
            orderGroup.setStatus(orderGroupApiRequest.getStatus())
                    .setOrderType(orderGroupApiRequest.getOrderType())
                    .setRevAddress(orderGroupApiRequest.getRevAddress())
                    .setRevName(orderGroupApiRequest.getRevName())
                    .setPaymentType(orderGroupApiRequest.getPaymentType())
                    .setTotalPrice(orderGroupApiRequest.getTotalPrice())
                    .setTotalQuantity(orderGroupApiRequest.getTotalQuantity())
                    .setOrderAt(orderGroupApiRequest.getOrderAt())
                    .setUser(userRepository.getOne(orderGroupApiRequest.getUserId()));

            return orderGroup;
        })
                .map(orderGroup -> orderGroupRepository.save(orderGroup))
                .map(updateOrderGroup->response(updateOrderGroup))
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<OrderGroup> optional = orderGroupRepository.findById(id);

        return optional
                .map(orderGroup -> {orderGroupRepository.delete(orderGroup);
                                    return Header.OK();
                                    })
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }


    public Header<OrderGroupApiResponse> response(OrderGroup orderGroup){
        //OrderGroup -> OrderGroupResponse
        OrderGroupApiResponse orderGroupApiResponse = OrderGroupApiResponse.builder()
                .id(orderGroup.getId())
                .status(orderGroup.getStatus())
                .orderType(orderGroup.getOrderType())
                .revAddress(orderGroup.getRevAddress())
                .revName(orderGroup.getRevName())
                .paymentType(orderGroup.getPaymentType())
                .totalPrice(orderGroup.getTotalPrice())
                .totalQuantity(orderGroup.getTotalQuantity())
                .orderAt(orderGroup.getOrderAt())
                .userId(orderGroup.getUser().getId())
                .build();
        return Header.OK(orderGroupApiResponse);
    }

}
