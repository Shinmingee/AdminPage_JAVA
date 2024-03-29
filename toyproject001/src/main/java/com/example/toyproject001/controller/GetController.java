package com.example.toyproject001.controller;

import com.example.toyproject001.model.SearchParam;
import com.example.toyproject001.model.network.Header;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GetController {

    @RequestMapping(method = RequestMethod.GET, path="/getMethod")
    public String getRequest(){

        return "Hi getMethod";
    }

    @GetMapping("/getParameter")
    public String getParameter(@RequestParam String id, @RequestParam String password){
        System.out.println("id "+id);
        System.out.println("password " + password);

        return id + password;
    }

    @GetMapping("/getMultiParameter")
    public SearchParam getMultiParameter(SearchParam searchParam){

        return searchParam;
    }

    @GetMapping("/header")
    public Header getHeader(){

        //{"resultCode":"OK", "description" : "OK"}
        return Header.builder().resultCode("OK").description("OK").build();
    }

}
