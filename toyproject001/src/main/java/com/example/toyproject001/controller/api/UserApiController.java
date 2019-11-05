package com.example.toyproject001.controller.api;

import com.example.toyproject001.controller.ifs.CrudInterface;
import com.example.toyproject001.model.network.Header;
import com.example.toyproject001.model.network.request.UserApiRequest;
import com.example.toyproject001.model.network.response.UserApiResponse;
import com.example.toyproject001.service.UserApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j //Simple Logging Facade for java
@RestController
@RequestMapping("/api/user")
public class UserApiController implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserApiLogicService userApiLogicService;

    @Override
    @PostMapping("") // api/user
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
        log.info("{}", request);
        return userApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}") // api/user/{id}
    public Header<UserApiResponse> read(@PathVariable(name="id") Long id) {

        return userApiLogicService.read(id);
    }

    @Override
    @PutMapping("") // api/user
    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> request) {

        return userApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}") // api/user/{id}
    public Header delete(@PathVariable(name="id") Long id) {

        return userApiLogicService.delete(id);
    }
}
