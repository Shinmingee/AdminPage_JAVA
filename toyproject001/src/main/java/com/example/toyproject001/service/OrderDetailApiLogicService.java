package com.example.toyproject001.service;

import com.example.toyproject001.controller.ifs.CrudInterface;
import com.example.toyproject001.model.entity.OrderDetail;
import com.example.toyproject001.model.network.Header;
import com.example.toyproject001.model.network.request.OrderDetailApiRequest;
import com.example.toyproject001.model.network.response.OrderDetailApiResponse;
import com.example.toyproject001.repository.ItemRepository;
import com.example.toyproject001.repository.OrderDetailRepository;
import com.example.toyproject001.repository.OrderGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderDetailApiLogicService implements CrudInterface<OrderDetailApiRequest, OrderDetailApiResponse> {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Override
    public Header<OrderDetailApiResponse> create(Header<OrderDetailApiRequest> request) {
        //request -> Entity -> Repository -> response

        //1. request->
        OrderDetailApiRequest body = request.getData();

        //2. Entity
        OrderDetail orderDetail = OrderDetail.builder()
                .status(body.getStatus())
                .arrivalDate(LocalDateTime.now())
                .quantity(body.getQuantity())
                .totalPrice(body.getTotalPrice())
                .createdAt(LocalDateTime.now())
                .createdBy(body.getCreatedBy())
                .updatedAt(body.getUpdatedAt())
                .updatedBy(body.getUpdatedBy())
                .item(itemRepository.getOne(body.getItemId()))
                .orderGroup(orderGroupRepository.getOne(body.getOrderGroupId()))
                .build();

        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);

        return Header.OK(response(newOrderDetail));
    }

    @Override
    public Header<OrderDetailApiResponse> read(Long id) {
        //id를 찾음
        Optional<OrderDetail> optional  = orderDetailRepository.findById(id);

        return optional.map(orderDetail -> response(orderDetail))
                .map(orderDetailApiResponse -> Header.OK(orderDetailApiResponse))
                .orElseGet(() -> Header.ERROR("데이터 없음"));

    }

    @Override
    public Header<OrderDetailApiResponse> update(Header<OrderDetailApiRequest> request) {
        //request -> id 비교 -> 해당 id에 맞게 set
        OrderDetailApiRequest orderDetailApiRequest = request.getData();

        Optional<OrderDetail> optional = orderDetailRepository.findById(orderDetailApiRequest.getId());

        return optional.map(orderDetail -> {
            orderDetail.setStatus(orderDetailApiRequest.getStatus())
                    .setArrivalDate(orderDetailApiRequest.getArrivalDate())
                    .setQuantity(orderDetailApiRequest.getQuantity())
                    .setTotalPrice(orderDetailApiRequest.getTotalPrice())
                    .setCreatedBy(orderDetailApiRequest.getCreatedBy())
                    .setCreatedAt(orderDetailApiRequest.getCreatedAt())
                    .setUpdatedAt(orderDetailApiRequest.getUpdatedAt())
                    .setUpdatedBy(orderDetailApiRequest.getUpdatedBy())
                    .setItem(itemRepository.getOne(orderDetailApiRequest.getItemId()))
                    .setOrderGroup(orderGroupRepository.getOne(orderDetailApiRequest.getOrderGroupId()));
            return orderDetail;
        })
                .map(orderDetail -> orderDetailRepository.save(orderDetail))
                .map(updateOrderDetail -> response(updateOrderDetail))
                .map(orderDetailApiResponse -> Header.OK(orderDetailApiResponse))
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<OrderDetail> optional = orderDetailRepository.findById(id);

        return optional.map(orderDetail -> {
            orderDetailRepository.delete(orderDetail);
            return Header.OK();

        })
                .orElseGet(()->Header.ERROR("데이터 없음"));

    }

    public OrderDetailApiResponse response(OrderDetail orderDetail){
        //Entity -> response
        OrderDetailApiResponse body = OrderDetailApiResponse.builder()
                .id(orderDetail.getId())
                .status(orderDetail.getStatus())
                .arrivalDate(orderDetail.getArrivalDate())
                .quantity(orderDetail.getQuantity())
                .totalPrice(orderDetail.getTotalPrice())
                .createdAt(orderDetail.getCreatedAt())
                .createdBy(orderDetail.getCreatedBy())
                .itemId(orderDetail.getItem().getId())
                .orderGroupId(orderDetail.getOrderGroup().getId())
                .build();

        return body;
    }

    public Header<List<OrderDetailApiResponse>> search(Pageable pageable){
        Page<OrderDetail> orderDetails = orderDetailRepository.findAll(pageable);

        List<OrderDetailApiResponse> orderDetailApiResponseList = orderDetails.stream()
                .map(orderDetail -> response(orderDetail))
                .collect(Collectors.toList());

        return Header.OK(orderDetailApiResponseList);
    }

}
