package com.example.toyproject001.controller.api;

import com.example.toyproject001.controller.ifs.CrudInterface;
import com.example.toyproject001.model.network.Header;
import com.example.toyproject001.model.network.request.OrderDetailApiRequest;
import com.example.toyproject001.model.network.response.OrderDetailApiResponse;
import com.example.toyproject001.repository.OrderDetailRepository;
import com.example.toyproject001.service.OrderDetailApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orderDetail")
public class OrderDetailApiController implements CrudInterface<OrderDetailApiRequest, OrderDetailApiResponse> {

    @Autowired
    private OrderDetailApiLogicService orderDetailApiLogicService;

    @Override
    @PostMapping("") // /api/orderDetail
    public Header<OrderDetailApiResponse> create(@RequestBody Header<OrderDetailApiRequest> request) {

        return orderDetailApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}") // /api/orderDetail/1
    public Header<OrderDetailApiResponse> read(@PathVariable Long id) {

        return orderDetailApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<OrderDetailApiResponse> update(@RequestBody Header<OrderDetailApiRequest> request) {

        return orderDetailApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {

        return orderDetailApiLogicService.delete(id);
    }
}
