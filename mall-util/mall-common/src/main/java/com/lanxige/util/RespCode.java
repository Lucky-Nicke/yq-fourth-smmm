package com.lanxige.util;

public enum RespCode {
    SUCCESS(20000,"成功"),
    ERROR(50000,"失败"),
    SYSTEM_ERROR(50001,"系统异常");

    private Integer code;
    private String msg;

    RespCode() {
    }

    RespCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
