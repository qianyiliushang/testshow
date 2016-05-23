package com.zombie.show.bean;

/**
 * Created by chenpengpeng on 16/5/23.
 */


public class RegisterRequest {
    private String phone;
    private String nick;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public static class Builder {
        private RegisterRequest registerRequest = new RegisterRequest();

        public Builder setPhone(String phone) {
            registerRequest.phone = phone;
            return this;
        }

        public Builder setNick(String nick) {
            registerRequest.nick = nick;
            return this;
        }

        public RegisterRequest getRegisterRequest() {
            return registerRequest;
        }

        public RegisterRequest build() {
            return registerRequest;
        }


    }

    public static Builder newBuilder() {
        Builder builder = new Builder();
        return builder;
    }
}
