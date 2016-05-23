package com.zombie.show.bean;

/**
 * Created by chenpengpeng on 16/5/23.
 */


public class TestBuilder {
    public static void main(String[] args) {
        RegisterRequest.Builder builder = RegisterRequest.newBuilder();
        RegisterRequest registerRequest = builder.setNick("zombie").setPhone("0954509834095").build();
        System.out.println(registerRequest.getNick());
        System.out.println(registerRequest.getPhone());
    }
}
