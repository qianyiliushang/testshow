package com.zombie.show.controller;

import com.zombie.show.bean.BaseResponse;
import com.zombie.show.bean.Greeting;
import com.zombie.show.bean.RegisterRequest;
import com.zombie.show.bean.RegisterResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 *
 */

@RestController
public class GreetingController {
    private static final String template = "Hello,%s";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "Word") String name) {
        return new Greeting(counter.getAndIncrement(), String.format(template, name));
    }

    @RequestMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest registerRequest) {
        RegisterResponse registerResponse = new RegisterResponse();
        BaseResponse baseResponse = new BaseResponse();
        registerResponse.setBaseResponse(baseResponse);
        if (registerRequest.getNick() != null) {
            registerResponse.setNick(registerRequest.getNick());
        } else {
            baseResponse.setErrCode(1000);
            baseResponse.setErrMsg("nick name cannot be null");
            return registerResponse;
        }
        if (registerRequest.getPhone() != null) {
            registerResponse.setPhone_number(registerRequest.getPhone());
        } else {
            baseResponse.setErrCode(1000);
            baseResponse.setErrMsg("nick name cannot be null");
            return registerResponse;
        }
        baseResponse.setErrMsg("OK");
        baseResponse.setErrCode(0);
        return registerResponse;
    }
}
