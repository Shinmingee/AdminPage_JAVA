package com.example.toyproject001.controller;


import com.example.toyproject001.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostController {

    @PostMapping (value = "/postMethod")
    public String postMethod(@RequestBody SearchParam searchParam){

        return "Hi postMethod";
    }

    @PutMapping("/putMethod")
    public void put(){

    }

    @PatchMapping("/patchMethod")
    public void patch(){

    }
}
