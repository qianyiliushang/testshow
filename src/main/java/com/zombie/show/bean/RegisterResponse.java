package com.zombie.show.bean;

/**
 * Created by chenpengpeng on 16/5/23.
 */


public class RegisterResponse {
    private BaseResponse baseResponse;
    private String phone_number;
    private String nick;

    public BaseResponse getBaseResponse() {
        return baseResponse;
    }

    public void setBaseResponse(BaseResponse baseResponse) {
        this.baseResponse = baseResponse;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
