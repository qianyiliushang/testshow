package com.zombie.show.bean;

/**
 * Created by chenpengpeng on 16/5/23.
 */


public class BaseResponse {
    private String errMsg;
    private Integer errCode;

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
